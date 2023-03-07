import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.TweenSpec
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*
import java.awt.Dimension
import kotlin.random.Random

@Composable
@Preview
fun App() {
    var curState by remember { mutableStateOf(State.INIT) }
    var isCurX by remember { mutableStateOf(Random.nextBoolean()) }
    var boyCount by remember { mutableStateOf(0) }
    var girlCount by remember { mutableStateOf(0) }
    var spermRes by remember { mutableStateOf(Constants.unknownSperm) }
    MaterialTheme {
        Column(
            modifier = Modifier.background(color = Color(0xfffcf8db)).fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = curState != State.INIT,
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
                        Crossfade(spermRes, animationSpec = TweenSpec(durationMillis = 1500)) {
                            LabeledImage(spermRes.first, spermRes.second)
                        }
                    }
                    Crossfade(curState, Modifier.fillMaxHeight().wrapContentHeight().weight(0.4f)) {
                        if (curState >= State.HAS_BABY && curState < State.COMPLETE) {
                            if (isCurX) BabyGirl() else BabyBoy()
                            Thread(Runnable {
                                Thread.sleep(500)
                                if (curState == State.HAS_BABY) {
                                    if (isCurX) girlCount++ else boyCount++
                                    curState = State.CALCULATION
                                    Thread.sleep(2000)
                                    curState = State.COMPLETE
                                    spermRes = Constants.unknownSperm
                                }
                            }).start()
                        } else {
                            BabyNoGender()
                        }
                    }
                    StatTable(
                        boyCount,
                        girlCount,
                        Modifier.fillMaxHeight().wrapContentHeight().weight(0.3f)
                    )
                }
            }
            val text = if (curState == State.INIT || curState == State.READY) {
                "点击\"开始\"按钮进行模拟"
            } else if (curState == State.COMPLETE) {
                "模拟已完成，点击\"开始\"按钮再次模拟"
            } else {
                "正在模拟..."
            }
            Text(
                modifier = Modifier.fillMaxWidth(15f).weight(0.1f).wrapContentHeight(),
                text = text,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(0.8f).weight(0.15f).wrapContentHeight()

            ) {
                ImageButton(
                    "start.png",
                    "开始",
                    Color.Black,
                    Color.Green,
                    curState == State.READY || curState == State.INIT || curState == State.COMPLETE
                ) {
                    curState = State.READY
                    Thread(Runnable {
                        Thread.sleep(1500)
                        curState = State.CREATE_SPERM
                        isCurX = Random.nextBoolean()
                        print("isCurX $isCurX")
                        spermRes = if (isCurX) Constants.spermX else Constants.spermY
                        Thread.sleep(1500)
                        curState = State.HAS_BABY
                    }).start()
                }
                val canClear = (boyCount + girlCount) != 0 && (curState == State.COMPLETE)
                ImageButton(
                    "clear.png", "清空", Color.Black, Color.Red, canClear
                ) {
                    boyCount = 0
                    girlCount = 0
                    spermRes = Constants.unknownSperm
                    curState = State.READY
                    println("clear clicked , state $curState")
                }
            }
        }
    }

}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "生男生女模拟器"
    ) {
        window.minimumSize = Dimension(1000, 625)
        App()
    }
}
