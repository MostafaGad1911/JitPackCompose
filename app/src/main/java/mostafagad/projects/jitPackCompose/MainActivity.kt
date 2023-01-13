package mostafagad.projects.jitPackCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mostafagad.projects.jitPackCompose.ui.theme.ComposeFirstTheme
import mostafagad.projects.compose.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFirstTheme {
                DiceRollerApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceRoller(
    )
}

@Composable
fun DiceRoller(modifier: Modifier = Modifier.fillMaxSize()) {
    var result by rememberSaveable {
        mutableStateOf(1)
    }
    val img = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = modifier.padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(img), contentDescription = "1")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.roll))
        }

    }
}



