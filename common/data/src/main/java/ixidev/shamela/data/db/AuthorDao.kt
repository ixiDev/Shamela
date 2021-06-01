package ixidev.shamela.data.db

import androidx.room.Dao
import androidx.room.Query
import ixidev.shamela.data.entities.Author

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */
@Dao
interface AuthorDao : BaseDao<Author> {

    @Query("SELECT * FROM author WHERE id=:authID LIMIT 1")
    suspend fun getById(authID: Int): Author
}