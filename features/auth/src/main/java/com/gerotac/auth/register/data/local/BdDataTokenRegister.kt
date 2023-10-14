package com.gerotac.auth.register.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gerotac.auth.register.data.local.entity.TokenRegisterEntity

@Database(
    entities = [TokenRegisterEntity::class],
    version = 1)
abstract class BdDataTokenRegister : RoomDatabase() {

    abstract val dao: DaoTokenRegister

    companion object {
        private var INSTANCE: BdDataTokenRegister? = null
        fun getInstance(context: Context): BdDataTokenRegister {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BdDataTokenRegister::class.java,
                        "todo_list_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}