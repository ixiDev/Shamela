package ixidev.shamela.data.db

import androidx.room.Dao
import androidx.room.Query
import ixidev.shamela.data.models.CategoryItemInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */

@Dao
interface ShamelaDao {


    @Query("SELECT count(bo.id) as 'count',cat.* FROM category cat,book bo WHERE cat.id=bo.category GROUP by cat.id")
    fun getAllCategories(): Flow<List<CategoryItemInfo>>

}