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
            style = TextStyle(fontSize = 20F.sp),
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
            modifier = Modifier.fillMaxWidth().padding(top = 8f.dp)
        )
    }
}

@Composable
@Preview
fun Egg(modifier: Modifier = Modifier) {
    LabeledImage("egg.png", "卵子", modifier)
}

@Composable
@Preview
fun SpermX(modifier: Modifier = Modifier) {
    LabeledImage("sperm_x.png", "带X染色体的精子", modifier)
}

@Composable
@Preview
fun SpermY(modifier: Modifier = Modifier) {
    LabeledImage("sperm_y.png", "带Y染色体的精子", modifier)
}

@Composable
@Preview
fun UnknownSperm(modifier: Modifier = Modifier) {
    LabeledImage("question_mark.png", "等待产生精子", modifier)
}

@Composable
@Preview
fun BabyBoy(modifier: Modifier = Modifier) {
    LabeledImage(
        "boy.svg", "小宝宝是男孩",  modifier.fillMaxSize()
    )
}

@Composable
@Preview
fun BabyGirl(modifier: Modifier = Modifier) {
    LabeledImage(
        "girl.svg", "小宝宝是女孩", modifier.fillMaxSize()
    )
}

@Composable
@Preview
fun BabyNoGender(modifier: Modifier = Modifier) {
    LabeledImage(
        "baby_no_gender.png", "小宝宝还未出生", modifier.fillMaxSize()
    )
}


@Composable
@Preview
fun SvgImage() {
    LabeledImage("boy.svg", "test")
}

