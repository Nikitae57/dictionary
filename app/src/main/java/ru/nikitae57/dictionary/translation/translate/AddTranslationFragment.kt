package ru.nikitae57.dictionary.translation.translate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nikitae57.dictionary.databinding.FragmentAddTranslationBinding
import javax.inject.Inject
import javax.inject.Provider

class AddTranslationFragment : MvpAppCompatFragment() {

    private lateinit var binding: FragmentAddTranslationBinding

    @Inject
    lateinit var presenterProvider: Provider<AddTranslationPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTranslationBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = AddTranslationFragment()
    }
}