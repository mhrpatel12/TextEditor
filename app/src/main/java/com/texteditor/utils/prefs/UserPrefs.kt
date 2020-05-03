package com.texteditor.utils.prefs

import com.texteditor.utils.constants.PrefConstants

class UserPrefs : BasePrefs() {
    companion object {

        private val prefName: String
            get() = PrefConstants.PREF_NAME_USER

        fun putValue(key: String, value: String) {
            BasePrefs.Companion.putValue(prefName, key, value)
        }

        fun putValue(key: String, value: Long) {
            BasePrefs.Companion.putValue(prefName, key, value)
        }

        fun putValue(key: String, value: Int) {
            BasePrefs.Companion.putValue(prefName, key, value)
        }

        fun putValue(key: String, value: Boolean) {
            BasePrefs.Companion.putValue(prefName, key, value)
        }

        @JvmOverloads
        fun getString(key: String, defaultValue: String? = null): String? {
            return BasePrefs.Companion.getString(prefName, key, defaultValue)
        }

        fun getLong(key: String): Long {
            return getLong(prefName, key)
        }

        fun getInt(key: String): Int {
            return getInt(prefName, key)
        }

        fun getBoolean(key: String): Boolean {
            return getBoolean(prefName, key)
        }
    }
}
