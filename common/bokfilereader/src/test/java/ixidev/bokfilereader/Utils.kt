package ixidev.bokfilereader

import java.io.File
import java.util.*

/**
 * Created by Abdelmajid ID ALI, on 28/05/2021
 * Github : [https://github.com/ixiDev]
 */


fun writeTableOfContent(bookId: Int, tableItem: Stack<ContentTableItem>) {

    val file = File("src/test/assets/$bookId.txt")
    file.delete()
    while (tableItem.isNotEmpty()) {
        val parent = tableItem.pop()
        if (!parent.subItems.isNullOrEmpty())
            writeTableOfContent(file, parent)
        else {

            val str = buildString {
                repeat(parent.item.lvl!!) {
                    append("\t")
                }
                append("-")
            }
            file.appendText("$str${parent.item.tit}\n")
        }
    }
}

fun writeTableOfContent(file: File, parent: ContentTableItem) {
    val str = buildString {
        repeat(parent.item.lvl!!) {
            append("\t")
        }
        append("-")
    }
    file.appendText("$str${parent.item.tit} \n")
    if (!parent.subItems.isNullOrEmpty())
        parent.subItems?.forEach {
            writeTableOfContent(file, it)
        }
}