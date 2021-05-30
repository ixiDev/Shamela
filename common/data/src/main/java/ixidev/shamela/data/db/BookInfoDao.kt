package ixidev.shamela.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import ixidev.shamela.data.entities.BookInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdelmajid ID ALI, on 29/05/2021
 * Github : [https://github.com/ixiDev]
 */

@Dao
interface BookInfoDao {

    @Update
    suspend fun update(item: BookInfo)

    @Query("SELECT * from book WHERE id=:id LIMIT 1")
    suspend fun getBookById(id: Int): BookInfo


    @Query("SELECT * from book")
    fun getAllBooks(): Flow<List<BookInfo>>

}