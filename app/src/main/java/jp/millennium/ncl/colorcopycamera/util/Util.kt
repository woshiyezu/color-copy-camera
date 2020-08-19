package jp.millennium.ncl.colorcopycamera.util

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:rgbColor")
fun rgbColor(view: View, rgbColor:String?){
    view.setBackgroundColor(Color.parseColor(rgbColor))
}