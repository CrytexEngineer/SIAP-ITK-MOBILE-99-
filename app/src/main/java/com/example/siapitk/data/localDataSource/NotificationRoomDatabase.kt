package com.example.siapitk.data.localDataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.siapitk.data.model.Notification

// Annotates class to be a Room Database with a table (entity) of the Notification class
@Database(entities = arrayOf(Notification::class), version = 1, exportSchema = false)
public abstract class NotificationRoomDatabase : RoomDatabase() {

    abstract fun notificationDao(): NotificationDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotificationRoomDatabase? = null

        fun getDatabase(context: Context): NotificationRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotificationRoomDatabase::class.java,
                    "notification_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}