package com.texteditor.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.texteditor.R
import com.texteditor.history.EditorState
import com.texteditor.history.History
import com.texteditor.utils.StringUtils
import com.texteditor.utils.StringUtils.countWords
import com.texteditor.utils.constants.PrefConstants
import com.texteditor.utils.prefs.BasePrefs

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val editor: MutableLiveData<String> = MutableLiveData()
    val words: MutableLiveData<String> = MutableLiveData()
    val focus: MutableLiveData<Boolean> = MutableLiveData()
    private val editorHistory = History()
    val undoAvailable: MutableLiveData<Boolean> = MutableLiveData()

    init {
        val initText =
            BasePrefs.getString(PrefConstants.PREF_NAME_USER, PrefConstants.PREF_KEY_EDIT_HISTORY)
        editor.value = initText
        focus.value = false
        undoAvailable.value = !TextUtils.isEmpty(initText)
        setWordCount(initText)
    }

    fun manageHistory(text: String) {
        if (TextUtils.isEmpty(text)) {
            setWordCount("")
            return
        }
        editor.value = text
        setWordCount(text)
        editorHistory.push(EditorState(text))
        undoAvailable.value = true
    }

    private fun setWordCount(string: String) {
        words.value = StringUtils.getString(R.string.no_of_words, countWords(string).toString())
    }

    fun undo() {
        val state = editorHistory.pop()
        if (state?.content == editor.value) {
            editor.value = editorHistory.getLastState()?.content
        } else {
            editor.value = state?.content
        }
        undoAvailable.value = editorHistory.list.isNotEmpty()
        setWordCount(if (TextUtils.isEmpty(editor.value)) "" else editor.value.toString())
    }

    fun clearFocus() {
        focus.value = false
    }

    override fun onCleared() {
        BasePrefs.putValue(
            PrefConstants.PREF_NAME_USER,
            PrefConstants.PREF_KEY_EDIT_HISTORY,
            editor.value.toString()
        )
        super.onCleared()
    }
}