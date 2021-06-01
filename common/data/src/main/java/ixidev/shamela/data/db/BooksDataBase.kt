package ixidev.shamela.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ixidev.shamela.data.entities.Author
import ixidev.shamela.data.entities.BookInfo
import ixidev.shamela.data.entities.Category

/**
 * Created by Abdelmajid ID ALI, on 29/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Database(
    entities = [BookInfo::class, Author::class, Category::class],
    version = 2,
    exportSchema = true
)
abstract class BooksDataBase : RoomDatabase() {

    abstract fun bookInfoDao(): BookInfoDao
    abstract fun authorDao(): AuthorDao
    abstract fun categoryDao(): CategoryDao
    abstract fun shamelaDao(): ShamelaDao

    companion object {

        fun create(context: Context): BooksDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                BooksDataBase::class.java,
                "shameladb.db"
            ).createFromAsset(
                "shameladb.db"
            ).build()
        }
    }

}