package mostafagad.projects.JitPackCompose

import android.content.Context
import android.content.res.Resources.Theme
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import mostafagad.projects.JitPackCompose.ui.theme.BLACK
import mostafagad.projects.JitPackCompose.ui.theme.ComposeFirstTheme
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
    Calculator(
    )
}

@Composable
fun Calculator() {
    var firstInput by rememberSaveable {
        mutableStateOf(
            ""
        )
    }

    var secondInput by rememberSaveable {
        mutableStateOf(
            ""
        )
    }

    var sum = 0f
    if (firstInput.isNotEmpty() && secondInput.isNotEmpty()) sum =
        secondInput.toFloatOrNull()?.let { firstInput.toFloatOrNull()?.plus(it) }!!

    var sub = 0f
    if (firstInput.isNotEmpty() && secondInput.isNotEmpty()) sub =
        secondInput.toFloatOrNull()?.let { firstInput.toFloatOrNull()?.minus(it) }!!

    var mul = 0f
    if (firstInput.isNotEmpty() && secondInput.isNotEmpty()) mul =
        firstInput.toFloatOrNull()?.times(secondInput.toFloatOrNull()!!)!!

    var div = 0f
    if (firstInput.isNotEmpty() && secondInput.isNotEmpty()) div =
        secondInput.toFloatOrNull()?.let { firstInput.toFloatOrNull()?.div(it) }!!

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .wrapContentSize(Alignment.Center)
            .padding(15.dp)
    ) {
        CircularProgressIndicatorSample()
        val context = LocalContext.current
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstInput,
            onValueChange = { input ->
                firstInput = input
            },
            label = {
                Text(stringResource(id = R.string.first_num))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = secondInput,
            onValueChange = { input ->
                secondInput = input
            },
            label = {
                Text(stringResource(id = R.string.second_num))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier.fillMaxWidth(), onClick = { sum.toString().mToast(context) }) {
            Text(text = stringResource(id = R.string.sum))
        }

        Spacer(modifier = Modifier.height(5.dp))

        Button(modifier = Modifier.fillMaxWidth(), onClick = { sub.toString().mToast(context) }) {
            Text(text = stringResource(id = R.string.sub))
        }

        Spacer(modifier = Modifier.height(5.dp))

        Button(modifier = Modifier.fillMaxWidth(), onClick = { mul.toString().mToast(context) }) {
            Text(text = stringResource(id = R.string.mul))
        }

        Spacer(modifier = Modifier.height(5.dp))

        Button(modifier = Modifier.fillMaxWidth(), onClick = { div.toString().mToast(context) }) {
            Text(text = stringResource(id = R.string.div))
        }

    }


}



fun String.mToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}


@Composable
fun CircularProgressIndicatorSample() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator()
        Spacer(Modifier.height(30.dp))
    }
}

