package ua.turskyi.visitedcountries.utils

import com.github.mikephil.charting.formatter.ValueFormatter

class IntFormatter: ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return value.toInt().toString()
    }
}