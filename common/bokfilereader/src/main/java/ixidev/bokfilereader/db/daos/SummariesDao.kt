package ixidev.bokfilereader.db.daos

import androidx.room.Dao
import androidx.room.Query
import ixidev.bokfilereader.entities.BookPage
import ixidev.bokfilereader.entities.BookSummary

/**
 * Created by Abdelmajid ID ALI, on 24/05/2021
 * Github : [https://github.com/ixiDev]
 */

@Dao
interface SummariesDao : BokFileBaseDao<BookSummary> {

    @Query("SELECT * FROM summary WHERE id=:id LIMIT 1")
    suspend fun getItemById(id: Int): BookSummary
}