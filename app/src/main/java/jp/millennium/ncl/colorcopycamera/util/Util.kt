package jp.millennium.ncl.colorcopycamera.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.androidadvance.topsnackbar.TSnackbar

const val PERMISSION_CAMERA = 234

fun TSnackbar.withColor(@ColorInt colorInt: Int): TSnackbar{
    this.view.setBackgroundColor(colorInt)
    return this
}

fun View.copyText(text: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", text)
    clipboardManager.setPrimaryClip(clipData)
}

@BindingAdapter("android:hexCode")
fun hexCode(view: View, hexCode:String?){
    hexCode?.let {
        view.setBackgroundColor(Color.parseColor(it))
    }
}