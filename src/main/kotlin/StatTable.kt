import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.lang.IllegalArgumentException

/**
 *
 *
 * @author wayneman
 * @since 2023/3/5
 */

@Composable
@Preview
fun RowScope.TableCell(text: String, txtColor: Color, weight: Float, isBold: Boolean = false) {
    Text(
        text,
        Modifier.border(1f.dp, Color.Black).weight(weight).fillMaxHeight().wrapContentHeight()
            .padding(8.dp),
        textAlign = TextAlign.Center,
        color = txtColor,
        fontWeight = if (isBold) FontWeight.Bold else null
    )
}

@Composable
@Preview
fun TableRow(
    txtList: List<String>,
    bgColor: Color = Color.Transparent,
    isHead: Boolean = false,
    txtColor: Color = Color.Black
) {
    if (txtList.size != 3) {
        throw IllegalArgumentException("must has 3 elements, $txtList")
    }

    Row(
        Modifier.height(60.dp).background(bgColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableCell(txtList[0], txtColor, 0.2f, isHead)
        TableCell(txtList[1], txtColor, 0.3f, isHead)
        TableCell(txtList[2], txtColor, 0.3f, isHead)
    }
}

@Composable
@Preview
fun StatTable(boyCount: Int, girlCount: Int, modifier: Modifier = Modifier) {
    val sum: Float = (boyCount + girlCount).toFloat()
    val boyRatio = if (sum == 0F) 0F else (boyCount / sum)
    val girlRatio = if (sum == 0F) 0F else (girlCount / sum)
    val total = if (sum == 0F) 0F else 1F
    Column(modifier = modifier) {
        TableRow(listOf("性别", "次数", "概率"), Color.Gray, true)
        TableRow(
            listOf("男孩", boyCount.toString(), String.format("%.2f", boyRatio)), Color(0xff5c79ee)
        )
        TableRow(
            listOf("女孩", girlCount.toString(), String.format("%.2f", girlRatio)),
            Color(0xffe09356)
        )
        TableRow(listOf("总计", sum.toInt().toString(), total.toString()), Color.LightGray)
    }
}