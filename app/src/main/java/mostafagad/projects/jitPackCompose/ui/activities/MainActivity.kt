package mostafagad.projects.jitPackCompose.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mostafagad.projects.compose.R
import mostafagad.projects.jitPackCompose.ui.theme.ComposeFirstTheme


class MainActivity : ComponentActivity() {

    private var provider = OAuthProvider.newBuilder("github.com")
    private val firebaseAuth: FirebaseAuth by lazy { Firebase.auth }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            ComposeFirstTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp), color = Color.DarkGray
                ) {
                    LoginWithGithub()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeFirstTheme {
            LoginWithGithub()
        }
    }





    @Composable
    fun LoginWithGithub() {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(start = 15.dp, end = 15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color.White,
                border = BorderStroke(2.dp, Color.Gray),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clickable {
                        loginWithGithub()
                    }
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(R.drawable.github),
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit, //https://stackoverflow.com/a/67153545
                        contentDescription = stringResource(R.string.github),
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .fillMaxWidth(),
                        text = stringResource(R.string.login_github),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                    )
                }


            }

        }
    }


    private fun loginWithGithub() {
        firebaseAuth
            .startActivityForSignInWithProvider( /* activity = */this, provider.build())
            .addOnSuccessListener {
                val profile = it.additionalUserInfo?.profile
                Log.i("GITHUB_PROFILE" , profile?.values.toString())
            }
            .addOnFailureListener {
                // Handle failure.
                Log.i("LOGIN_EXCEPTION" , it.message.toString())
            }

    }

}


