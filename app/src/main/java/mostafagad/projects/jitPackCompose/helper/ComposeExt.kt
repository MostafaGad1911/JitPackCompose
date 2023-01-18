package mostafagad.projects.jitPackCompose.helper

import android.content.Context
import android.widget.Toast

fun String.toast(ctx: Context){
    Toast.makeText(ctx , this , Toast.LENGTH_LONG).show()
}
