package ixidev.shamela.data

import ixidev.shamela.data.db.BooksDataBase
import ixidev.shamela.data.entities.BookInfo
import ixidev.shamela.data.models.CategoryItemInfo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */

class ShamelaRepository(private val booksDataBase: BooksDataBase) {


    fun getAllCategories(): Flow<List<CategoryItemInfo>> {
        return booksDataBase.shamelaDao().getAllCategories()
    }

    fun getBooksByCategory(categoryId: Int): Flow<List<BookInfo>> {
        return booksDataBase.bookInfoDao().getByCategory(categoryId)
    }
}