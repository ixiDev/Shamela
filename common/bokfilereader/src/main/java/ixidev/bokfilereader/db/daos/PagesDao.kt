package ixidev.bokfilereader.db.daos

import androidx.room.Dao
import androidx.room.Query
import ixidev.bokfilereader.entities.BookPage
import ixidev.bokfilereader.tables.TTable

/**
 * Created by Abdelmajid ID ALI, on 24/05/2021
 * Github : [https://github.com/ixiDev]
 */

@Dao
interface PagesDao : BokFileBaseDao<BookPage> {

    @Query("SELECT * FROM pages WHERE id=:id LIMIT 1")
    suspend fun getItemById(id: Int): BookPage
}