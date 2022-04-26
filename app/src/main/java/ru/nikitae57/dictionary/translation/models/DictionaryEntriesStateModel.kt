package ru.nikitae57.dictionary.translation.models

data class DictionaryEntriesStateModel(
    val entries: List<DictionaryEntryStateModel>
) {
    companion object {
        fun createEmpty() = DictionaryEntriesStateModel(emptyList())
    }
}
