package com.example.composelearning.design

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearning.viewModel.LoginPojo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScree(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    uiState: LoginPojo
) {
    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var funcMultiply: (String, String) -> LoginPojo = { a: String, b: String ->
        LoginPojo(name = a, password = b)

    }

    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(), verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = {

                name = it
                uiState.name = it

            },
            label = { Text(text = "Name") },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .border(1.dp, Color.Gray, shape = RectangleShape), singleLine = true

        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
                uiState.password = it

            },
            label = { Text(text = "Password") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .border(width = 1.dp, color = Color.Gray, shape = RectangleShape), singleLine = true
        )
        CreateButton(
            text = "Login",
            modifier.align(Alignment.CenterHorizontally),
            onClick
        )
    }


}


@Composable
fun CreateButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF0F9D58))

    ) {
        Text(text = text, color = Color.White)
    }
}

fun validation(name: String, password: String, context: Context): Boolean {
    var flag: Boolean = false
    if (name.isEmpty()) {
        flag = true
        Toast.makeText(context, "Enter Name", Toast.LENGTH_SHORT).show()
    }

    if (password.isEmpty()) {
        flag = true
        Toast.makeText(context, "Enter Name", Toast.LENGTH_SHORT).show()
    }
    return flag
}

@Preview(showSystemUi = true)
@Composable
fun showLogin() {
    LoginScree(Modifier,{},LoginPojo("rahul","tyagi"))
}