package ixidev.bokfilereader.db

import ixidev.bokfilereader.entities.BookPage
import ixidev.bokfilereader.entities.BookSummary
import ixidev.bokfilereader.popAllIf
import ixidev.bokfilereader.tables.BTable
import ixidev.bokfilereader.tables.MainTable
import ixidev.bokfilereader.tables.TTable
import ixidev.bokfilereader.toBookSummary
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Abdelmajid ID ALI, on 24/05/2021
 * Github : [https://github.com/ixiDev]
 */
class BokFileRepository(private val database: BokFileDataBase) {


    suspend fun insertBookSummary(items: List<TTable>): Int {
        val summaryItems = parseSummaryItems(items)
        return database.summaryDao()
            .insert(*summaryItems.toTypedArray())
            .size
    }

    private fun parseSummaryItems(tElements: List<TTable>): ArrayList<BookSummary> {
        val summary = arrayListOf<BookSummary>()
        val levels = tElements.mapIndexed { index, tTable ->
            index to tTable.lvl!!
        }.reversed()

        val levelsStack = Stack<Pair<Int, Int>>()

        levels.forEach { (itemIndex, itemLvl) ->
            val items = levelsStack.popAllIf { (_, lvl) ->
                itemLvl < lvl
            }
            if (items.isNotEmpty()) {
                summary.addAll(items.map { (index, _) ->
                    tElements[index].toBookSummary(id = index + 1, itemIndex + 1)
                })
            }
            levelsStack.add(itemIndex to itemLvl)
        }
        while (levelsStack.isNotEmpty()) {
            val (index, _) = levelsStack.pop()
            summary.add(tElements[index].toBookSummary(index + 1))
        }
        return summary
    }

    suspend fun insertBookPages(items: List<BTable>): Int {
        val pages = items.map { item ->
            BookPage(
                page = item.page!!,
                part = item.part!!,
                content = item.nass!!
            )
        }
        return database.pagesDao().insert(*pages.toTypedArray()).size
    }

    suspend fun insertBookMain(bookMain: MainTable): Long {
        return database.mainTableDao().insert(bookMain)
    }
}