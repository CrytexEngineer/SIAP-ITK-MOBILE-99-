package com.example.siapitk.data.localDataSource


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.siapitk.data.model.Notification

@Dao
interface NotificationDao {

    @Query("SELECT * from notification  ORDER BY id DESC")
    fun getAllNotification(): LiveData<List<Notification>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notification: Notification)

    @Query("DELETE FROM notification")
    suspend fun deleteAll()
}