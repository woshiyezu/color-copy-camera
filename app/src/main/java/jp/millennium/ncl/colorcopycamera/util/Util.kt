package jp.millennium.ncl.colorcopycamera.util

import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar

fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar{
    this.view.setBackgroundColor(colorInt)
    return this
}

@BindingAdapter("android:rgbColor")
fun rgbColor(view: View, rgbColor:String?){
    view.setBackgroundColor(Color.parseColor(rgbColor))
}