import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *
 *
 * @author wayneman
 * @since 2023/3/4
 */

@Composable
@Preview
fun ImageButton(
    src: String,
    txt: String,
    txtColor: Color,
    imgColor: Color,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.wrapContentHeight(),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(src),
            contentDescription = "ICON",
            modifier = Modifier.size(32f.dp).padding(4f.dp),
            colorFilter = ColorFilter.tint(imgColor)
        )
        Text(
            txt,
            color = txtColor,
            style = TextStyle(fontSize = 24F.sp),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
@Preview
fun LabeledImage(src: String, label: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(src),
            contentDescription = "EGG",
            modifier = Modifier.fillMaxWidth(0.4f).aspectRatio(52f / 56f, true)
        )
        Text(
            label,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.fillMaxWidth().padding(top = 8f.dp)
        )
    }
}
