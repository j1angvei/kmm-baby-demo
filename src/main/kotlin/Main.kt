import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*
import java.awt.Dimension
import kotlin.random.Random

@Composable
@Preview
fun App() {
    var canNextStep by remember { mutableStateOf(true) }
    var curStep by remember { mutableStateOf(Step.INIT) }
    var isCurX by remember { mutableStateOf(Random.nextBoolean()) }
    var boyCount by remember { mutableStateOf(0) }
    var girlCount by remember { mutableStateOf(0) }
    var spermRes by remember { mutableStateOf(Constants.unknownSperm) }
    var babyStatus by remember { mutableStateOf(Constants.babyUnknown) }
    MaterialTheme {
        Column(
            modifier = Modifier.background(color = Color(0xfffcf8db)).fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = curStep != Step.INIT || (boyCount + girlCount != 0),
                modifier = Modifier.fillMaxWidth().weight(0.75f).background(Color.Transparent)
                    .wrapContentHeight()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(0.3f).fillMaxHeight()
                    ) {
                        Egg()
                        Image(
                            painter = painterResource("plus.png"),
                            contentDescription = "PLUS",
                            colorFilter = ColorFilter.tint(Color.Gray),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                                .fillMaxWidth(0.05f)
                        )
                        LabeledImage(spermRes.first, spermRes.second)
                    }
                    LabeledImage(
                        babyStatus.first,
                        babyStatus.second,
                        Modifier.fillMaxHeight().wrapContentHeight().weight(0.4f)
                    )
                    StatTable(
                        boyCount,
                        girlCount,
                        Modifier.fillMaxHeight().wrapContentHeight().weight(0.3f)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(0.8f).weight(0.25f).wrapContentHeight()

            ) {
                ImageButton(
                    "start.png", curStep.text, Color.Black, Color.Green, canNextStep
                ) {
                    canNextStep = false
                    when (curStep) {
                        Step.READY -> {
                            isCurX = Random.nextBoolean()
                            spermRes = if (isCurX) Constants.spermX else Constants.spermY
                        }

                        Step.FERTILIZED -> {
                            babyStatus = Constants.fertilization
                        }

                        Step.GENDER -> {
                            babyStatus = if (isCurX) Constants.babyGirl else Constants.babyBoy
                        }

                        Step.CALCULATE -> {
                            if (isCurX) girlCount++ else boyCount++
                        }

                        Step.COMPLETE -> {
                            spermRes = Constants.unknownSperm
                            babyStatus = Constants.babyUnknown
                        }

                        else -> {}
                    }
                    Thread(Runnable {
                        Thread.sleep(500)
                        curStep = curStep.next()
                        canNextStep = true
                    }).start()
                }
                val canClear =
                    (boyCount + girlCount) != 0 && (curStep == Step.COMPLETE)
                ImageButton(
                    "clear.png", "清空数据", Color.Black, Color.Red, canClear
                ) {
                    boyCount = 0
                    girlCount = 0
                    spermRes = Constants.unknownSperm
                    babyStatus = Constants.babyUnknown
                    curStep = Step.INIT
                    println("clear clicked , state $curStep")
                }
            }
        }
    }

}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "生男生女模拟器"
    ) {
        window.minimumSize = Dimension(1024, 768)
        App()
    }
}
