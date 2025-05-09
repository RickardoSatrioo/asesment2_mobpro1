package com.rickardosatrioabout.asesment2_mobpro1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rickardosatrioabout.asesment2_mobpro1.model.Ukm

@Database(entities = [Ukm::class], version = 1, exportSchema = false)
abstract class UkmDb : RoomDatabase() {

    abstract val dao: UkmDao

    companion object {

        @Volatile
        private var INSTANCE: UkmDb? = null

        fun getInstance(context: Context): UkmDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UkmDb::class.java,
                        "ukm.db"
                    ).build()
                    return instance
                }
                return instance
            }
        }
    }
}