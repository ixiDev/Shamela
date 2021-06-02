package ixidev.shamela.data.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Abdelmajid ID ALI, on 30/05/2021
 * Github : [https://github.com/ixiDev]
 */
@RunWith(AndroidJUnit4::class)
class BooksDataBaseTest {

    private lateinit var dataBase: BooksDataBase

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dataBase = BooksDataBase.create(context)
    }

    @Test
    fun get_book_by_id(): Unit = runBlocking {
        val item = dataBase.bookInfoDao().getBookById(1)
        assertEquals(item.name, "الفواكه العذاب في الرد على من لم يحكم السنة والكتاب")
    }

    @Test
    fun get_category_by_id(): Unit = runBlocking {
        val item = dataBase.categoryDao().getById(1)
        assertEquals(item.name, "العقيدة")
    }

    @Test
    fun get_author_by_id(): Unit = runBlocking {
        val item = dataBase.authorDao().getById(1)
        assertEquals(item.name, "الواحدي")
    }

}