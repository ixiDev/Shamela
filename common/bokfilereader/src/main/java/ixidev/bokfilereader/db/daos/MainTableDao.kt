package ixidev.bokfilereader.db.daos

import androidx.room.Dao
import androidx.room.Query
import ixidev.bokfilereader.tables.MainTable

/**
 * Created by Abdelmajid ID ALI, on 28/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Dao
interface MainTableDao : BokFileBaseDao<MainTable> {

    @Query("SELECT * FROM book_main WHERE BkId=:id LIMIT 1")
    suspend fun getItemById(id: Int): MainTable
}