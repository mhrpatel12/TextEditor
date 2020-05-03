package com.texteditor.utils

import com.texteditor.TextEditorApplication

object StringUtils {
    fun countWords(string: String): Int {
        return string.trim().split("([[a-z][A-Z][0-9][\\Q-\\E]]+)".toRegex()).toTypedArray().size + string.trim().replace(
            "([[a-z][A-Z][0-9][\\W]]*)".toRegex(),
            ""
        ).length - 1
    }

    fun getString(resId: Int, formatArgs: Any): String {
        return TextEditorApplication.context.getString(resId, formatArgs)
    }
}