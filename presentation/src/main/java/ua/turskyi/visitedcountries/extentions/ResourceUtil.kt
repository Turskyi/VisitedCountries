package ua.turskyi.visitedcountries.extentions

import android.content.Context
import androidx.annotation.DimenRes

fun Context.pixelsFromSpResource(@DimenRes sizeRes: Int): Float {
    return resources.getDimension(sizeRes) / resources.displayMetrics.density
}
