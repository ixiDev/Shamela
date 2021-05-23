package ixidev.bokfilereader

import android.os.FileUtils
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.File
import java.io.FileOutputStream

/**
 * Created by Abdelmajid ID ALI, on 21/05/2021
 * Github : [https://github.com/ixiDev]
 */
@RunWith(AndroidJUnit4::class)
class BokFileReaderInstrumentTest {

    private val bookId: Int = 151016
    private lateinit var reader: BokFileReader

    @Before
    fun readFile() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val out = File(appContext.cacheDir, "$bookId.bok")
        FileUtils.copy(appContext.assets.open("$bookId.bok"), FileOutputStream(out))
        reader = BokFileReader(out.path)
    }

    @Test
    fun readMainTable() {
        val bokFile = reader.getBokFile()
        assert(bokFile.main.BkId != null)
        assertEquals(bokFile.main.BkId, bookId)
    }

    @Test
    fun readBTable() {
        val bokFile = reader.getBokFile()
        val bElements = bokFile.bElements
        assert(bElements.isNotEmpty())
    }

    @Test
    fun readTTable() {
        val bokFile = reader.getBokFile()
        val tElements = bokFile.tElements
        assert(tElements.isNotEmpty())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun createTestData() = arrayOf(151045, 151100, 151007, 151016)
    }

}