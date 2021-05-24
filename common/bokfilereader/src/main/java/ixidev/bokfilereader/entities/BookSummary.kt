package ixidev.bokfilereader.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdelmajid ID ALI, on 24/05/2021
 * Github : [https://github.com/ixiDev]
 */

@Entity(tableName = "summary")
data class BookSummary(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val page: Int,
    val parent: Int,
    val title: String
)