package ru.nikitae57.dictionary.translation.savedtranslations

import androidx.recyclerview.widget.DiffUtil
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel

class TranslationItemDiffCallback(
    private val old: DictionaryEntriesStateModel,
    private val new: DictionaryEntriesStateModel,
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.entries.size

    override fun getNewListSize() = new.entries.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old.entries[oldItemPosition].words.size ==
                new.entries[newItemPosition].words.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old.entries[oldItemPosition].words == new.entries[newItemPosition].words
    }
}