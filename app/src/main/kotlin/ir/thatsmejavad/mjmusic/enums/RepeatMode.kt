package ir.thatsmejavad.mjmusic.enums

enum class RepeatMode(val value: Int) {
    REPEAT_MODE_OFF(0),
    REPEAT_MODE_ONE(1),
    REPEAT_MODE_ALL(2);

    companion object {
        fun getFromValue(value: Int): RepeatMode {
            return RepeatMode.entries.find { it.value == value } ?: REPEAT_MODE_OFF
        }
    }
}
