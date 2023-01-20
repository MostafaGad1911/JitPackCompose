package mostafagad.projects.jitPackCompose.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import mostafagad.projects.compose.R
import mostafagad.projects.jitPackCompose.data.models.MenuItem

@Composable
fun DrawerHeader(userName: String, avatar: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(avatar),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(1.dp, Color.Gray, CircleShape)   // add a border (optional)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = userName,
                fontSize = 25.sp,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 15.dp)
            )
        }

    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit,
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(item)
                }
                .padding(16.dp)) {
                Image(
                    painter =  painterResource(item.icon),
                    contentDescription = "avatar" // add a border (optional)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title, style = itemTextStyle, modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
