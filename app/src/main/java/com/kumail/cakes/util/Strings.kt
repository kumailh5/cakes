package com.kumail.cakes.util

import androidx.annotation.StringRes
import com.kumail.cakes.CakesApp

/**
 * Created by kumailhussain on 16/11/2021.
 */
object Strings {
    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return CakesApp.instance.getString(stringRes, *formatArgs)
    }
}