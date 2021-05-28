package ixidev.bokfilereader

import android.os.FileUtils
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
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
@RunWith(Parameterized::class)
class BokFileReaderInstrumentTest(
    private val bookId: Int
) {

    private lateinit var reader: BokFileReader
    private lateinit var bokFile: BokFile

    @Before
    fun readFile() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val out = File(appContext.cacheDir, "$bookId.bok")
        FileUtils.copy(appContext.assets.open("$bookId.bok"), FileOutputStream(out))
        reader = BokFileReader(out.path)
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
//        val stack = Stack<TTable>()
//        stack.addAll(tElements)
//        val tableOfContent = Stack<ContentTableItem>()
//        while (stack.isNotEmpty()) {
//            val item = stack.pop()
//            val sub: List<ContentTableItem> = tableOfContent.popAllIf {
//                it.item.lvl != 1 && item.lvl!! < it.item.lvl!!
//            }
//            tableOfContent.add(ContentTableItem(item, sub))
//        }
//        val topLevels = tElements.count {
//            it.lvl == 1
//        }
        assert(tElements.isNotEmpty())
    }

//    @Test
//    fun readTTable1() {
//        val tElements = bokFile.tElements
//
//        val summary = arrayListOf<BookSummary>()
//        val levels = tElements.mapIndexed { index, tTable ->
//            index to tTable.lvl!!
//        }.reversed()
//        val levelsStack = Stack<Pair<Int, Int>>()
//        levels.forEach { (itemIndex, itemLvl) ->
//            val items = levelsStack.popAllIf { (_, lvl) ->
//                itemLvl < lvl
//            }
//            if (items.isNotEmpty()) {
//                summary.addAll(items.map { (index, _) ->
//                    tElements[index].toBookSummary(id = index + 1, itemIndex + 1)
//                })
//            }
//            levelsStack.add(itemIndex to itemLvl)
//        }
//        while (levelsStack.isNotEmpty()) {
//            val (index, _) = levelsStack.pop()
//            summary.add(tElements[index].toBookSummary(index + 1))
//        }
//        val topLevels = tElements.count {
//            it.lvl == 1
//        }
//        val summaryTopLevelsSize = summary.count {
//            it.parent == 0
//        }
//        assertEquals(topLevels, summaryTopLevelsSize)
//    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun createTestData() = arrayOf(151045, 151100, 151007, 151016, 11055)
    }

}