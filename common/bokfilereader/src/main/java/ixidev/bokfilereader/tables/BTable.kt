package ixidev.bokfilereader.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdelmajid ID ALI, on 21/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Entity
data class BTable(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0,
    val id: Int? = null,
    val nass: String? = null,
    val part: Int? = null,
    val page: Int? = null
)