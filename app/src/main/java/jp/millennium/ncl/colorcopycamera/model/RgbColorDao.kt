package jp.millennium.ncl.colorcopycamera.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RgbColorDao {

    @Insert
    suspend fun insert(rgbColor : RgbColor)

    @Query("SELECT * FROM rgbColor")
    suspend fun getAllRgbColor():List<RgbColor>
}