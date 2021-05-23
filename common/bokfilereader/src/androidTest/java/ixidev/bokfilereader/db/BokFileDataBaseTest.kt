package ixidev.bokfilereader.db

import android.content.Context
import android.os.FileUtils
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import ixidev.bokfilereader.BokFileReader
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by Abdelmajid ID ALI, on 23/05/2021
 * Github : [https://github.com/ixiDev]
 */

@RunWith(AndroidJUnit4::class)
class BokFileDataBaseTest {

    private lateinit var context: Context
    private val bookId: Int = 151007
    private lateinit var db: BokFileDataBase
    private lateinit var reader: BokFileReader

    @Before
    fun createDatabase() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        context.deleteDatabase("$bookId")
        db = BokFileDataBase.create(context, bookId)
        val out = File(context.cacheDir, "$bookId.bok")
        FileUtils.copy(context.assets.open("$bookId.bok"), FileOutputStream(out))
        reader = BokFileReader(out.path)
        db.clearAllTables()
    }

    @Test
    fun test_insert_bok_file() = runBlocking {
        val bokFile = reader.getBokFile()
        db.mainTableDao().insert(bokFile.main)
        val itemById = db.mainTableDao().getItemById(bokFile.main.BkId!!)
        assert(itemById == bokFile.main)
    }


    @Test
    fun test_insert_ttable_items() = runBlocking {
        val tTableElements = reader.getBokFile().tElements
        val ids = db.tTableDao().insert(*tTableElements.toTypedArray())
        assertEquals(tTableElements.size, ids.size)

    }

    @Test
    fun test_insert_btable_items() = runBlocking {
        val bTableElements = reader.getBokFile().bElements
        val ids = db.bTableDao().insert(*bTableElements.toTypedArray())
        assertEquals(bTableElements.size, ids.size)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


}