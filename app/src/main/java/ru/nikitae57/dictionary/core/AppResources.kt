package ru.nikitae57.dictionary.core

import android.content.Context
import androidx.annotation.StringRes
import ru.nikitae57.domain.core.Res
import javax.inject.Inject

class AppResources @Inject constructor(
    private val context: Context
) : Res {
    override fun getString(@StringRes id: Int) = context.getString(id)
}