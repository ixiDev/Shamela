package ixidev.bokfilereader.db.daos

import androidx.room.Dao
import androidx.room.Query
import ixidev.bokfilereader.tables.BTable
import ixidev.bokfilereader.tables.MainTable

/**
 * Created by Abdelmajid ID ALI, on 23/05/2021
 * Github : [https://github.com/ixiDev]
 */

@Dao
interface BTableDao : BokFileBaseDao<BTable> {

    @Query("SELECT * FROM btable WHERE id=:id LIMIT 1")
    suspend  fun getItemById(id: Int): BTable
}