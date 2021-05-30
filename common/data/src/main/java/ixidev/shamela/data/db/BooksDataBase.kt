package ixidev.shamela.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ixidev.shamela.data.entities.BookInfo

/**
 * Created by Abdelmajid ID ALI, on 29/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Database(entities = [BookInfo::class], version = 2, exportSchema = true)
abstract class BooksDataBase : RoomDatabase() {

    abstract fun bookInfoDao(): BookInfoDao

    companion object {

        fun create(context: Context): BooksDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                BooksDataBase::class.java,
                "books.db"
            ).createFromAsset(
                "books.db"
            ).build()
        }
    }

}