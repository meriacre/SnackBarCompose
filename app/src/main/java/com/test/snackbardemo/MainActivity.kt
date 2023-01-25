package com.test.snackbardemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.snackbardemo.ui.theme.SnackBarDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackBarDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DisplaySnackBar()
                }
            }
        }
    }
}

@Composable
fun DisplaySnackBar(){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val myCoroutineScope = rememberCoroutineScope()

        Scaffold(scaffoldState = scaffoldState) {
            Button(onClick = {
                myCoroutineScope.launch {
                   val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = "this is the message",
                        actionLabel = "Undo",
//                        duration = SnackbarDuration.Long
                        duration = SnackbarDuration.Indefinite
                    )

                    when(snackBarResult){
                        SnackbarResult.ActionPerformed -> Log.i("MyTag", "Action label clicked")
                        SnackbarResult.Dismissed -> Log.i("MyTag", "Action label dismissed")
                    }
                }
            }) {
                Text(text = "Display SnackBar")
            }
        }
}