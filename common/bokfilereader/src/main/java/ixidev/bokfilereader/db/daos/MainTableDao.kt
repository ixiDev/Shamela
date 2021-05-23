package ixidev.bokfilereader.db.daos

import androidx.room.*
import ixidev.bokfilereader.tables.MainTable

/**
 * Created by Abdelmajid ID ALI, on 23/05/2021
 * Github : [https://github.com/ixiDev]
 */

@Dao
interface MainTableDao : BokFileBaseDao<MainTable> {

    @Query("SELECT * FROM maintable WHERE BkId=:id LIMIT 1")
    suspend  fun getItemById(id: Int): MainTable

}