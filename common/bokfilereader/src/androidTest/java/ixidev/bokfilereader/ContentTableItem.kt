package ixidev.bokfilereader

import ixidev.bokfilereader.tables.TTable

/**
 * Created by Abdelmajid ID ALI, on 26/05/2021
 * Github : [https://github.com/ixiDev]
 */

data class ContentTableItem(
    val item: TTable,
    val subItems: List<ContentTableItem>? = null
)