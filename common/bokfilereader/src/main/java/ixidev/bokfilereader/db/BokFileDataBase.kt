package ixidev.bokfilereader.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ixidev.bokfilereader.db.daos.BTableDao
import ixidev.bokfilereader.db.daos.MainTableDao
import ixidev.bokfilereader.db.daos.TTableDao
import ixidev.bokfilereader.tables.BTable
import ixidev.bokfilereader.tables.MainTable
import ixidev.bokfilereader.tables.TTable

/**
 * Created by Abdelmajid ID ALI, on 23/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Database(
    entities = [MainTable::class, BTable::class, TTable::class],
    version = 1
)
abstract class BokFileDataBase : RoomDatabase() {


    abstract fun mainTableDao(): MainTableDao
    abstract fun tTableDao(): TTableDao
    abstract fun bTableDao(): BTableDao

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