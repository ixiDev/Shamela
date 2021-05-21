package ixidev.bokfilereader

import ixidev.bokfilereader.tables.BTable
import ixidev.bokfilereader.tables.MainTable
import ixidev.bokfilereader.tables.TTable

/**
 * Created by Abdelmajid ID ALI, on 21/05/2021
 * Github : [https://github.com/ixiDev]
 */
data class BokFile(
    val main: MainTable,
    val bElements: List<BTable>,
    val tElements: List<TTable>
)