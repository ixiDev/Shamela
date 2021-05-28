package ixidev.bokfilereader

import ixidev.bokfilereader.entities.BookSummary
import ixidev.bokfilereader.tables.TTable
import java.util.*

/**
 * Created by Abdelmajid ID ALI, on 28/05/2021
 * Github : [https://github.com/ixiDev]
 */
// super fun
fun <E> Stack<E>.popAllIf(predicate: (E) -> Boolean): List<E> {
    val items = arrayListOf<E>()
    if (this.isEmpty())
        return items
    while (this.isNotEmpty() && predicate.invoke(this.peek()))
        items.add(this.pop())
    return items
}

fun <E> List<E>.copy(): List<E> {
    val copy = arrayListOf<E>()
    copy.addAll(this)
    return copy
}

fun TTable.toBookSummary(id: Int = 0, parent: Int = 0): BookSummary {
    return BookSummary(
        id = id,
        page = this.id!!,
        parent = parent,
        level = lvl!!,
        title = this.tit!!
    )
}