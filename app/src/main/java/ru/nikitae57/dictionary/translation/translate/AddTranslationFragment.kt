package ru.nikitae57.dictionary.translation.translate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nikitae57.dictionary.core.BackButtonListener
import ru.nikitae57.dictionary.databinding.FragmentAddTranslationBinding
import ru.nikitae57.dictionary.getTranslationsComponent
import ru.nikitae57.dictionary.isRunningTest
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
        initDi()
        super.onAttach(context)
    }

    private fun initDi() {
        if (!isRunningTest) {
            getTranslationsComponent().inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddTranslationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun showLoadingState() {
        with(binding) {
            globalProgressBar.visibility = View.VISIBLE
            addButton.visibility = View.INVISIBLE
            swapLanguagesButton.visibility = View.INVISIBLE
            translationProgressBar.visibility = View.INVISIBLE
            fromLanguagesSpinner.visibility = View.INVISIBLE
            fromLanguageLabel.visibility = View.INVISIBLE
            toLanguagesSpinner.visibility = View.INVISIBLE
            toLanguageLabel.visibility = View.INVISIBLE
            divider.visibility = View.INVISIBLE
            wordInputLayout.visibility = View.INVISIBLE
            translatedWord.visibility = View.INVISIBLE
            errorMessage.visibility = View.INVISIBLE
        }
    }

    override fun showTranslationLoadingState() {
        with(binding) {
            translationProgressBar.visibility = View.VISIBLE
            translatedWord.visibility = View.INVISIBLE
            addButton.isEnabled = false
            swapLanguagesButton.isEnabled = false
            errorMessage.visibility = View.INVISIBLE
        }
    }

    override fun showTranslation(translation: CharSequence, shouldBlockTranslateButton: Boolean) {
        with(binding) {
            translationProgressBar.visibility = View.INVISIBLE
            errorMessage.visibility = View.INVISIBLE
            translatedWord.apply {
                text = translation
                visibility = View.VISIBLE
            }
            swapLanguagesButton.isEnabled = true
            addButton.isEnabled = !shouldBlockTranslateButton
        }
    }

    override fun updateLanguages(fromLanguageLabels: List<CharSequence>, toLanguageLabels: List<CharSequence>) = with(binding) {
        fromLanguagesSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            fromLanguageLabels
        )
        toLanguagesSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            toLanguageLabels
        )
    }

    override fun showSuccessState(state: AddTranslationStateModel.Success) {
        with(binding) {
            addButton.apply {
                isEnabled = false
                text = state.addButtonText
                visibility = View.VISIBLE
            }
            swapLanguagesButton.apply {
                setImageDrawable(ContextCompat.getDrawable(requireContext(), state.swapLanguagesIconId))
                visibility = View.VISIBLE
            }
            wordInputLayout.hint = state.wordInputHint
            fromLanguagesSpinner.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                state.fromLanguageLabels
            )
            toLanguagesSpinner.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                state.toLanguageLabels
            )
            toLanguageLabel.text = state.toLanguagesSpinnerLabel
            fromLanguageLabel.text = state.fromLanguagesSpinnerLabel

            errorMessage.visibility = View.INVISIBLE
            globalProgressBar.visibility = View.INVISIBLE
            translationProgressBar.visibility = View.INVISIBLE
            fromLanguagesSpinner.visibility = View.VISIBLE
            fromLanguageLabel.visibility = View.VISIBLE
            toLanguagesSpinner.visibility = View.VISIBLE
            toLanguageLabel.visibility = View.VISIBLE
            divider.visibility = View.VISIBLE
            wordInputLayout.visibility = View.VISIBLE
        }
    }

    override fun showErrorState(state: AddTranslationStateModel.Error) {
        with(binding) {
            translationProgressBar.visibility = View.INVISIBLE
            errorMessage.apply {
                visibility = View.VISIBLE
                text = state.errorMessage
            }
        }
    }

    private fun setListeners() = binding.run {
        addButton.setOnClickListener { presenter.onSaveTranslation() }
        wordInputEditText.addTextChangedListener { editable ->
            val fromLanguage = fromLanguagesSpinner.selectedItem
            val toLanguage = toLanguagesSpinner.selectedItem
            if (editable != null && toLanguage != null && fromLanguage != null) {
                presenter.onChangeTextToTranslate(text = editable.toString())
            }
        }
        swapLanguagesButton.setOnClickListener { presenter.onSwapLanguages() }
        fromLanguagesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onFromLanguageChanged(fromLanguagesSpinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        toLanguagesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onToLanguageChanged(toLanguagesSpinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    companion object {
        fun newInstance() = AddTranslationFragment()
    }
}