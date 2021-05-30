package ixidev.shamela

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ixidev.shamela.ui.home.ShamelaBottomBar
import ixidev.shamela.ui.home.ShamelaTopAppBar
import ixidev.shamela.ui.theme.ShamelaTheme


private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShamelaTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(elevation = 4.dp) {
                                ShamelaTopAppBar()
                            }
                        },
                        bottomBar = {
                            ShamelaBottomBar {
                                Log.d(TAG, "onCreate: $it")
                            }
                        }
                    ) {
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShamelaTheme {
        Greeting("Android")
    }
}