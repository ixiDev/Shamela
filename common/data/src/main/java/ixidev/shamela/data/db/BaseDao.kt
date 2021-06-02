package ixidev.shamela.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */
interface BaseDao<Entity : Any> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: Entity): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg item: Entity): List<Long>

    @Update
    suspend fun update(item: Entity)

    @Delete
    suspend fun delete(item: Entity)

}