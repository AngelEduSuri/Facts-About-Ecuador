package com.aesuriagasalazar.aboutecuador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aesuriagasalazar.aboutecuador.ui.EcuadorAppBar
import com.aesuriagasalazar.aboutecuador.ui.EcuadorBodyScreen
import com.aesuriagasalazar.aboutecuador.ui.theme.AboutEcuadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AboutEcuadorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EcuadorMainApp()
                }
            }
        }
    }
}


@Composable
fun EcuadorMainApp() {
    Scaffold(
        topBar = {
            EcuadorAppBar(title = R.string.app_name)
        }
    ) {
        EcuadorBodyScreen(modifier = Modifier.padding(paddingValues = it))
    }
}
