package ixidev.bokfilereader.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdelmajid ID ALI, on 24/05/2021
 * Github : [https://github.com/ixiDev]
 */

@Entity(tableName = "pages")
data class BookPage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val page: Int,
    val part: Int,
    val content: String
)