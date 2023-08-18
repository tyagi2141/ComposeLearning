package com.example.composelearning.design

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DashBoard (name:String,password:String){

    Column {
        Text(text = name)
        Text(text = password)
    }
}

@Preview(showSystemUi = true)
@Composable
fun dashboardView(){
    DashBoard(name = "orio", password = "123")
}