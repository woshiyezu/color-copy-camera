package jp.millennium.ncl.colorcopycamera.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.millennium.ncl.colorcopycamera.model.RgbColor

class RgbColorViewModel:ViewModel() {

    val rgbColorList = MutableLiveData<List<RgbColor>>()

    fun refresh(){
        val newRgbColorList: MutableList<RgbColor> = mutableListOf()
        newRgbColorList.add(RgbColor("#112233"))
        newRgbColorList.add(RgbColor("#334455"))
        newRgbColorList.add(RgbColor("#898989"))
        rgbColorListRetrived(newRgbColorList)
    }

    private fun rgbColorListRetrived(newRgbColorList:List<RgbColor>) {
        rgbColorList.value = newRgbColorList
    }

}