package ixidev.shamela.data.db

import androidx.room.Dao
import androidx.room.Query
import ixidev.shamela.data.entities.Category

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */
@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("SELECT * FROM category WHERE id=:catId LIMIT 1")
    suspend fun getById(catId: Int): Category
}