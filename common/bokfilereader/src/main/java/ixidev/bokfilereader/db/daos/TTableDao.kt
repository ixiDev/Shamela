package ixidev.bokfilereader.db.daos

import androidx.room.Dao
import androidx.room.Query
import ixidev.bokfilereader.tables.TTable

/**
 * Created by Abdelmajid ID ALI, on 23/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Dao
interface TTableDao : BokFileBaseDao<TTable> {

    @Query("SELECT * FROM ttable WHERE id=:id LIMIT 1")
    suspend fun getItemById(id: Int): TTable
}