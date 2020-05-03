package com.texteditor.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.texteditor.R
import com.texteditor.databinding.ActivityMainBinding
import com.texteditor.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainViewModel.focus.observe(this, Observer { focus ->
            if (!focus) binding.txtEditor.clearFocus()
        })
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.txtEditor.setOnFocusChangeListener { view, isFocused ->
            if (!isFocused) {
                mainViewModel.manageHistory(binding.txtEditor.text.toString())
                view.let { v ->
                    val imm =
                        v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        binding.btnUndo.setOnClickListener {
            mainViewModel.undo()
        }
        binding.root.setOnClickListener {
            mainViewModel.clearFocus()
        }
    }
}