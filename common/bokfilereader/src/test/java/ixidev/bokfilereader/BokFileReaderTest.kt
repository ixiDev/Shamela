package ixidev.bokfilereader

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


/**
 * Created by Abdelmajid ID ALI, on 21/05/2021
 * Github : [https://github.com/ixiDev]
 */
@RunWith(Parameterized::class)
class BokFileReaderTest(
    private val bookId: Int
) {
    private lateinit var reader: BokFileReader
    private lateinit var bokFile: BokFile

    @Before
    fun readFile() {
        reader = BokFileReader("src/test/assets/$bookId.bok")
        bokFile = reader.getBokFile()
    }

    @Test
    fun readMainTable() {
        assert(bokFile.main.BkId != null)
        assertEquals(bokFile.main.BkId, bookId)
    }

    @Test
    fun readBTable() {
        val bElements = bokFile.bElements
        assert(bElements.isNotEmpty())
    }

    @Test
    fun readTTable() {
        val tElements = bokFile.tElements
        assert(tElements.isNotEmpty())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun createTestData() = arrayOf(
            151007,
            11055,
            151007,
            151016,
            151045,
            151100
        )
    }

}