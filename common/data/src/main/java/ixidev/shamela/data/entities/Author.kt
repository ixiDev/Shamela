package ixidev.shamela.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */

@Entity
data class Author(
    @PrimaryKey
    val id: Long = 0,
    @ColumnInfo(name = "is_deleted")
    val isDeleted: String? = null,
    val name: String? = null,
    val biography: String? = null,
    @ColumnInfo(name = "death_text")
    val deathText: String? = null,
    @ColumnInfo(name = "death_number")
    val deathNumber: String? = null,
)