package ru.nikitae57.dictionary

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun textInputLayoutHasHint(hint: String): TypeSafeMatcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false
        return item.hint == hint
    }
}