package ru.nikitae57.dictionary.translation.savedtranslations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel
import ru.nikitae57.dictionary.translation.models.DictionaryEntryStateModel

class TranslationItemAdapter(
    var dictionaryEntriesStateModel: DictionaryEntriesStateModel
) : RecyclerView.Adapter<TranslationItemAdapter.TranslationItemViewHolder>() {

    private val entries: List<DictionaryEntryStateModel>
        get() = dictionaryEntriesStateModel.entries

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.translations_list_item, parent, false)
        return TranslationItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TranslationItemViewHolder, position: Int) {
        val inflater = LayoutInflater.from(holder.itemView.context)

        holder.wordsList.removeAllViews()
        dictionaryEntriesStateModel.entries[position].words.forEach {
            val view = inflater.inflate(
                R.layout.words_list_item, holder.itemView as CardView, false
            ).apply {
                findViewById<TextView>(R.id.word).text = it.text
                findViewById<TextView>(R.id.languageLabel).text = it.languageLabel
            }

            holder.wordsList.addView(view)
        }
        holder.itemView.requestLayout()
    }

    override fun getItemCount() = entries.size

    class TranslationItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordsList: LinearLayout = view.findViewById(R.id.wordsList)
    }
}