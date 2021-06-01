package ixidev.shamela.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */
@Entity
data class Category(
    @PrimaryKey
    val id: Long = 0,
    @ColumnInfo(name = "is_deleted")
    var isDeleted: String? = null,
    val order: Long = 0,
    val name: String? = null
)