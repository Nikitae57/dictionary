package ru.nikitae57.dictionary.translation.translate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nikitae57.dictionary.core.BackButtonListener
import ru.nikitae57.dictionary.databinding.FragmentAddTranslationBinding
import ru.nikitae57.dictionary.getAppComponent
import ru.nikitae57.dictionary.translation.di.DaggerTranslationComponent
import ru.nikitae57.dictionary.translation.models.WordStateModel
import javax.inject.Inject
import javax.inject.Provider

class AddTranslationFragment : MvpAppCompatFragment(), AddTranslationView, BackButtonListener {

    private lateinit var binding: FragmentAddTranslationBinding

    @Inject
    lateinit var presenterProvider: Provider<AddTranslationPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerTranslationComponent.builder()
            .appComponent(getAppComponent())
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddTranslationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            wordInputEditText.addTextChangedListener { editable ->
                editable?.let {
                    presenter.onChangeTextToTranslate(text = it.toString(), languageLabel = "ru") // TODO scratch code
                }
            }
        }
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun showLoadingState() {
        with(binding) {
            globalProgressBar.visibility = View.VISIBLE
            addButton.visibility = View.GONE
            translationProgressBar.visibility = View.GONE
            languagesSpinner.visibility = View.GONE
            divider.visibility = View.GONE
            wordInputLayout.visibility = View.GONE
        }
    }

    override fun showTranslationLoadingState() {
        with(binding) {
            translationProgressBar.visibility = View.VISIBLE
            translatedWord.visibility = View.GONE
            // addButton.isEnabled = false
        }
    }

    override fun showTranslation(translation: WordStateModel) {
        with(binding) {
            translationProgressBar.visibility = View.GONE
            addButton.isEnabled = true
            translatedWord.apply {
                text = translation.text
                visibility = View.VISIBLE
            }
        }
    }

    override fun showSuccessState(state: AddTranslationStateModel.Success) {
        with(binding) {
            addButton.apply {
                isEnabled = true
                text = state.addButtonText
            }
            wordInputLayout.hint = state.wordInputHint

            globalProgressBar.visibility = View.GONE
            translationProgressBar.visibility = View.GONE
            addButton.visibility = View.VISIBLE
            languagesSpinner.visibility = View.VISIBLE
            divider.visibility = View.VISIBLE
            wordInputLayout.visibility = View.VISIBLE
        }
    }

    override fun showErrorState(state: AddTranslationStateModel.Error) {
        context?.let {
            Toast.makeText(it, state.errorMessage, Toast.LENGTH_LONG)
        }
    }

    companion object {
        fun newInstance() = AddTranslationFragment()
    }
}