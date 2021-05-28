package ixidev.bokfilereader.db

import android.content.Context
import android.os.FileUtils
import androidx.test.platform.app.InstrumentationRegistry
import ixidev.bokfilereader.BokFile
import ixidev.bokfilereader.BokFileReader
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by Abdelmajid ID ALI, on 23/05/2021
 * Github : [https://github.com/ixiDev]
 */

@RunWith(Parameterized::class)
class BokFileDataBaseTest(
    private val bookId: Int
) {

    private lateinit var context: Context
    private lateinit var db: BokFileDataBase
    private lateinit var repository: BokFileRepository
    private lateinit var bokFile: BokFile

    @Before
    fun createDatabase() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        context.deleteDatabase("$bookId")
        db = BokFileDataBase.create(context, bookId)
        val out = File(context.cacheDir, "$bookId.bok")
        FileUtils.copy(context.assets.open("$bookId.bok"), FileOutputStream(out))
        db.clearAllTables()
        val reader = BokFileReader(out.path)
        bokFile = reader.getBokFile()
        repository = BokFileRepository(db)
    }

    @Test
    fun test_insert_bok_file() = runBlocking {
        val id = repository.insertBookMain(bokFile.main)
        val itemById = db.mainTableDao().getItemById(bokFile.main.BkId!!)
        assertEquals(itemById, bokFile.main)
        assertEquals(bokFile.main.BkId!!, id.toInt())
    }


    @Test
    fun test_insert_ttable_items() = runBlocking {
        val tElements = bokFile.tElements
        val size = repository.insertBookSummary(tElements)
        assertEquals(tElements.size, size)

    }

    @Test
    fun test_insert_btable_items() = runBlocking {
        val bElements = bokFile.bElements
        val size = repository.insertBookPages(bElements)
        assertEquals(bElements.size, size)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun createTestData() = arrayOf(151045, 151100, 151007, 151016, 11055)
    }

}