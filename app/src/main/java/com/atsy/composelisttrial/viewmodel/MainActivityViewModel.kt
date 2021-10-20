package com.atsy.composelisttrial.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    // 監視可能なリスト
    var todoItems = mutableStateListOf<String>()
        private set
}