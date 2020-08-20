package jp.millennium.ncl.colorcopycamera.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RgbColor(

    @ColumnInfo(name = "color_code")
    val code: String)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}