package com.texteditor.history

class History {
    val list: ArrayList<EditorState> = ArrayList()

    fun push(state: EditorState) {
        list.add(state)
    }

    fun pop(): EditorState? {
        val lastState = getLastState()
        list.remove(lastState)
        return lastState
    }

    fun getLastState(): EditorState? {
        val lastIndex = list.size - 1
        if (lastIndex == -1) return null
        return list[lastIndex]
    }
}