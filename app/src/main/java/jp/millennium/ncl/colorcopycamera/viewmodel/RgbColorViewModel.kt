package jp.millennium.ncl.colorcopycamera.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import jp.millennium.ncl.colorcopycamera.model.RgbColor
import jp.millennium.ncl.colorcopycamera.model.RgbColorDataBase
import kotlinx.coroutines.launch

class RgbColorViewModel(application: Application):BaseViewModel(application) {

    val rgbColorList = MutableLiveData<List<RgbColor>>()

    fun fetch() {
        launch {
            val newRgbColorList = RgbColorDataBase(getApplication()).rgbColorDao().getAllRgbColor()
            rgbColorListRetrived(newRgbColorList)
        }
    }

    fun remove(rgbColor: RgbColor) {
        launch {
            RgbColorDataBase(getApplication()).rgbColorDao().delete(rgbColor)
        }
    }

    private fun rgbColorListRetrived(newRgbColorList:List<RgbColor>) {
        rgbColorList.value = newRgbColorList
    }

}