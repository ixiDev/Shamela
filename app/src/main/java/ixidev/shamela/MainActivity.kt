package ixidev.shamela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ixidev.shamela.ui.home.*
import ixidev.shamela.ui.theme.ShamelaTheme


@Suppress("unused")
private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShamelaTheme {
                val navController = rememberNavController()
                val viewModel: HomeViewModel = viewModel()
                Scaffold(
                    topBar = {
                        TopAppBar(elevation = 4.dp) {
                            ShamelaTopAppBar()
                        }
                    },
                    content = {
                        CreateNavigation(navController, viewModel)
                    },
                    bottomBar = {
                        CreateBottomBar(navController)
                    },
                )
            }
        }
    }

    @Composable
    private fun CreateNavigation(
        navController: NavHostController,
        viewModel: HomeViewModel
    ) {
        NavHost(
            navController = navController,
            startDestination = "allBooks",
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            composable("allBooks") {
                HomePageView(viewModel) { bookId: Int ->
                    navController.navigate("book/${bookId}")
                }
            }
            composable("myBooks") {
                Box {
                    Text(text = "You Dont Have any book")
                }
            }
            composable("book/{id}") {

                Text(text = "${it.arguments?.get("id")}")
            }

        }
    }

    @Composable
    private fun CreateBottomBar(navController: NavHostController) {
        BottomAppBar {
            ShamelaBottomBar {
                when (it) {
                    is BottomBarItem.MyBooks -> {
                        navController.navigate("myBooks")
                    }
                    else -> {
                        navController.navigate("AllBooks")
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