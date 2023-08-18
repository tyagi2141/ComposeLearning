package com.example.composelearning

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearning.design.LoginScree
import com.example.composelearning.route.ScreenRoute
import com.example.composelearning.ui.theme.ComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // MessageList()
                   // LoginScree(modifier = Modifier, this)

                    ScreenRoute()

                }
            }
        }
    }
}


@Composable
fun MessageList() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        for (i in 1..30) {
            Greeting("Android $i")
            Log.e("result...", "$i")
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(6.dp)) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(80.dp)
                .background(Color.LightGray)
                .border(2.dp, Color.Transparent, shape = RoundedCornerShape(20.dp))
                .padding(6.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "image",
                Modifier
                    .clip(CircleShape)
                    .border(0.1.dp, color = Color.Gray, CircleShape)
                    .size(50.dp), contentScale = ContentScale.Crop, alignment = Alignment.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "$name", fontWeight = FontWeight.Bold)
                Spacer(
                    modifier = Modifier
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                )
                Text(text = "content", fontWeight = FontWeight.Light)

            }
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeLearningTheme {
        Greeting("Android")
    }
}


