package ru.nikitae57.dictionary.translation.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.translation.models.DictionaryEntryStateModel

class WordItemAdapter(
    private val dictionaryEntry: DictionaryEntryStateModel
) : BaseAdapter() {
    override fun getCount() = dictionaryEntry.words.size

    override fun getItem(position: Int) = dictionaryEntry.words[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.words_list_item, parent, false)

        return view.apply {
            findViewById<TextView>(R.id.languageLabel).text = dictionaryEntry.words[position].languageLabel
            findViewById<TextView>(R.id.word).text = dictionaryEntry.words[position].text
        }
    }
}