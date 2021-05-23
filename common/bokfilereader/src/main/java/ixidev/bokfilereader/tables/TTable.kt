package ixidev.bokfilereader.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdelmajid ID ALI, on 21/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Entity
data class TTable(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0,
    val id: Int? = null,
    val tit: String? = null,
    val lvl: Int? = null,
    val sub: Int? = null
)