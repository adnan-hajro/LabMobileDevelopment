package ibu.edu.ba.myapplication

import ibu.edu.ba.myapplication.ui.screens.CustomGalleryView
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ibu.edu.ba.myapplication.ui.ImageViewModel
import ibu.edu.ba.myapplication.ui.screens.SendEmailView
import ibu.edu.ba.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                SendEmailView()
                //CustomGalleryView(viewModel = viewModel)
            }
        }

//        intent?.let {
//            if (it.action == Intent.ACTION_SEND && it.type?.startsWith("image/") == true) {
//                val imageUri: Uri? = it.getParcelableExtra(Intent.EXTRA_STREAM)
//                imageUri?.let { uri ->
//                    viewModel.addImage(uri) // Update ViewModel with the shared image
//                }
//            }
//        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("Main activity: ", "onNewIntent called")
        intent.let {
            if (it.action == Intent.ACTION_SEND && it.type?.startsWith("image/") == true) {
                val imageUri: Uri? = it.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
                imageUri?.let { uri ->
                    viewModel.addImage(uri) // Update ViewModel with the shared image
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}