package ixidev.shamela.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdelmajid ID ALI, on 29/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Entity(tableName = "book")
data class BookInfo(
    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo(name = "is_deleted")
    var isDeleted: String? = null,
    var name: String? = null,
    var category: Int? = 0,
    var type: String? = null,
    var date: Int? = 0,
    var author: Int? = 0,
    var printed: String? = null,
    @ColumnInfo(name = "minor_release")
    var minorRelease: String? = null,
    @ColumnInfo(name = "major_release")
    var majorRelease: String? = null,
    var bibliography: String? = null,
    var hint: String? = null,
    @ColumnInfo(name = "pdf_links")
    var pdfLinks: String? = null,
    var metadata: String? = null,
)