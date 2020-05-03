package com.texteditor.utils.prefs

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.texteditor.TextEditorApplication
import com.texteditor.utils.constants.PrefConstants
import java.util.*

open class BasePrefs {
    companion object {
        private val TAG = BasePrefs::class.java.simpleName
        private val TAG1 = "$TAG-Write"
        private val TAG2 = "$TAG-Read"
        private val TAG3 = "$TAG-Delete"

        fun getPrefs(prefName: String): SharedPreferences? {
            return TextEditorApplication.context.getSharedPreferences(
                prefName,
                Context.MODE_PRIVATE
            )
        }

        fun getEditor(prefName: String): SharedPreferences.Editor? {
            val prefs = getPrefs(prefName)
            return prefs?.edit()
        }

        fun removePref(prefName: String) {
            val editor = getEditor(prefName)
            editor?.clear()
        }

        fun putValue(prefName: String, key: String, value: String) {
            try {
                val editor = getEditor(prefName) ?: return
                editor.putString(key, value)
                editor.apply()
            } catch (e: Exception) {
            }
        }

        fun putValue(prefName: String, key: String, value: Long) {
            try {
                val editor = getEditor(prefName) ?: return
                editor.putLong(key, value)
                editor.apply()
            } catch (e: Exception) {
            }
        }

        fun putValue(prefName: String, key: String, value: Int) {
            try {
                val editor = getEditor(prefName) ?: return
                editor.putInt(key, value)
                editor.apply()
            } catch (e: Exception) {
            }
        }

        fun putValue(prefName: String, key: String, value: Boolean) {
            try {
                val editor = getEditor(prefName) ?: return
                editor.putBoolean(key, value)
                editor.apply()
            } catch (e: Exception) {
            }
        }

        fun putValue(prefName: String, key: String, value: Float) {
            try {
                val editor = getEditor(prefName) ?: return
                editor.putFloat(key, value)
                editor.apply()
            } catch (e: Exception) {
            }
        }

        fun putStringSet(prefName: String, key: String, value: Set<String>?) {
            try {
                val editor = getEditor(prefName) ?: return
                editor.putStringSet(key, value)
                editor.apply()
                if (value != null) {
                }
            } catch (e: Exception) {
            }
        }

        @JvmOverloads
        fun getString(prefName: String, key: String, defaultValue: String? = null): String {
            val prefs = getPrefs(prefName)
            val value = prefs?.getString(key, defaultValue)
            return if (TextUtils.isEmpty(value)) "" else value!!
        }

        @JvmOverloads
        fun getLong(prefName: String, key: String, defaultValue: Long = 0L): Long {
            val prefs = getPrefs(prefName)
            val value = prefs?.getLong(key, defaultValue) ?: 0L
            return value
        }

        @JvmOverloads
        fun getInt(prefName: String, key: String, defaultValue: Int = 0): Int {
            val prefs = getPrefs(prefName)
            val value = prefs?.getInt(key, defaultValue) ?: 0
            return value
        }

        @JvmOverloads
        fun getBoolean(prefName: String, key: String, defaultVal: Boolean? = false): Boolean {
            val prefs = getPrefs(prefName)
            val value = prefs?.getBoolean(key, defaultVal!!) ?: defaultVal
            return value!!
        }

        @JvmOverloads
        fun getFloat(prefName: String, key: String, defaultVal: Float = 0f): Float {
            val prefs = getPrefs(prefName)
            val value = prefs?.getFloat(key, defaultVal) ?: defaultVal
            return value
        }

        fun getStringSet(prefName: String, key: String): Set<String> {
            val defaultValue = HashSet<String>()
            val prefs = getPrefs(prefName)
            val value = if (prefs != null) prefs.getStringSet(key, defaultValue) else defaultValue
            val valueCopy = HashSet<String>()
            valueCopy.addAll(value!!)
            return valueCopy
        }

        fun isKey(prefName: String, key: String): Boolean {
            val prefs = getPrefs(prefName)
            return prefs?.contains(key) ?: false
        }

        fun removeKey(prefName: String, key: String) {
            val prefs = getPrefs(prefName)
            prefs?.edit()?.remove(key)?.apply()
        }

        fun getProperty(key: String, property: String, max: Int): Boolean {
            if (!isKey(PrefConstants.PREF_NAME_USER, key)) {
                val isInit = Random().nextInt(max) != 0
                putValue(PrefConstants.PREF_NAME_USER, key, isInit)
            }
            return getBoolean(PrefConstants.PREF_NAME_USER, key)
        }
    }
}
