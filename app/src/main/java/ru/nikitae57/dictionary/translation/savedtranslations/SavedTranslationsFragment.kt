package ru.nikitae57.dictionary.translation.savedtranslations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nikitae57.dictionary.databinding.FragmentSavedTranslationsBinding
import ru.nikitae57.dictionary.getTranslationsComponent
import ru.nikitae57.dictionary.isRunningTest
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel
import javax.inject.Inject
import javax.inject.Provider

class SavedTranslationsFragment : MvpAppCompatFragment(), SavedTranslationsView {

    private lateinit var binding: FragmentSavedTranslationsBinding

    @Inject
    lateinit var presenterProvider: Provider<SavedTranslationsPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun onAttach(context: Context) {
        initDi()
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
        presenter.loadSavedTranslations()
    }

    private fun initDi() {
        if (!isRunningTest) {
            getTranslationsComponent().inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSavedTranslationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            fab.setOnClickListener {
                presenter.onAddTranslationClicked()
            }
            searchInputEditText.addTextChangedListener { editable ->
                editable?.let {
                    presenter.onSearchTextChanged(it.toString())
                }
            }
            translationsList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TranslationItemAdapter(
                    DictionaryEntriesStateModel.createEmpty()
                )
            }
        }
    }

    override fun showInitialState(state: SavedTranslationsStateModel.Initial) {
        binding.searchInputLayout.hint = state.textInputHintText
    }

    override fun showLoadingState() {
        binding.apply {
            errorNotification.visibility = View.INVISIBLE
            translationsList.visibility = View.INVISIBLE
            searchInputLayout.visibility = View.INVISIBLE
            fab.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun showSuccessState(state: SavedTranslationsStateModel.Success) {
        updateTranslationsList(state.dictionaryEntriesStateModel)
        with(binding) {
            searchInputLayout.visibility = View.VISIBLE
            translationsList.visibility = View.VISIBLE
            fab.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
            errorNotification.visibility = View.INVISIBLE
        }
    }

    override fun showErrorState(state: SavedTranslationsStateModel.Error) {
        binding.apply {
            errorMessage.text = state.errorMessage
            tryAgainButton.apply {
                text = state.tryAgainButtonText
                setOnClickListener { state.tryAgainAction() }
            }

            errorNotification.visibility = View.VISIBLE
            searchInputLayout.visibility = View.INVISIBLE
            progressBar.visibility = View.INVISIBLE
            translationsList.visibility = View.INVISIBLE
            fab.visibility = View.INVISIBLE
        }
    }

    private fun updateTranslationsList(dictionaryEntriesStateModel: DictionaryEntriesStateModel) {
        val adapter = binding.translationsList.adapter as TranslationItemAdapter
        val callback = TranslationItemDiffCallback(
            old = adapter.dictionaryEntriesStateModel,
            new = dictionaryEntriesStateModel
        )
        val diffResult = DiffUtil.calculateDiff(callback)

        adapter.dictionaryEntriesStateModel = dictionaryEntriesStateModel
        diffResult.dispatchUpdatesTo(adapter)
    }

    companion object {
        fun newInstance() = SavedTranslationsFragment()
    }
}