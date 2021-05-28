package ixidev.bokfilereader.db.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Created by Abdelmajid ID ALI, on 23/05/2021
 * Github : [https://github.com/ixiDev]
 */
interface BokFileBaseDao<Entity : Any> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: Entity): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg item: Entity): List<Long>

    @Update
    suspend fun update(item: Entity)

    @Delete
    suspend fun delete(item: Entity)

}