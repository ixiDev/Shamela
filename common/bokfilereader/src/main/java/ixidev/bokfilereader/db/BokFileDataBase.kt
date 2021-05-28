package ixidev.bokfilereader.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ixidev.bokfilereader.db.daos.MainTableDao
import ixidev.bokfilereader.db.daos.PagesDao
import ixidev.bokfilereader.db.daos.SummariesDao
import ixidev.bokfilereader.entities.BookPage
import ixidev.bokfilereader.entities.BookSummary
import ixidev.bokfilereader.tables.MainTable

/**
 * Created by Abdelmajid ID ALI, on 23/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Database(
    entities = [BookPage::class, BookSummary::class, MainTable::class],
    version = 1,
    exportSchema = false
)
abstract class BokFileDataBase : RoomDatabase() {


    abstract fun pagesDao(): PagesDao
    abstract fun summaryDao(): SummariesDao
    abstract fun mainTableDao(): MainTableDao

    companion object {
        fun create(context: Context, bokId: Int): BokFileDataBase {
            return Room.databaseBuilder(
                context,
                BokFileDataBase::class.java,
                "$bokId"
            ).build()
        }
    }
}