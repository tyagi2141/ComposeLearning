package com.example.composelearning.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


//import androidx.compose.runtime.setValue

class LoginViewModel() : ViewModel() {

    var uiStateLogin by mutableStateOf(LoginPojo())


}


data class LoginPojo(var name: String? = null, var password: String? = null)