/**
 *
 *
 * @author wayneman
 * @since 2023/3/7
 */
enum class Step(val text: String, val hint: String) {
    START_SIMULATION("开始模拟", "点击\"开始\"按钮进入模拟"),
    NEW_SPERM("产生精卵", "点击\"模拟\"按钮产生精子"),
    FERTILIZED("开始受精", "点击\"受精\"按钮开始受精"),
    GENDER("查看性别", "点击\"性别\"按钮查看性别"),
    CALCULATE("计算概率", "点击\"计算\"按钮更新比例"),
    COMPLETE("模拟完成", "点击\"完成\"按钮进入下次模拟");

    fun next(): Step {
        val size = values().size
        val nextOrdinal = (ordinal + 1) % size
        return Step.values()[nextOrdinal]
    }
}