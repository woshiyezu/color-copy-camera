package jp.millennium.ncl.colorcopycamera.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RgbColor::class), version = 1)
abstract class RgbColorDataBase: RoomDatabase() {

    abstract fun rgbColorDao():RgbColorDao

    companion object {
        @Volatile private var instance:RgbColorDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?: buildDataBase(context).also {
                instance = it
            }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(context.applicationContext,RgbColorDataBase::class.java,"rgbcolordatabase").build()
    }

}