package ixidev.bokfilereader

import ixidev.bokfilereader.tables.BTable
import ixidev.bokfilereader.tables.MainTable
import ixidev.bokfilereader.tables.TTable


data class BokFile(
    val main: MainTable,
    val bElements: List<BTable>,
    val tElements: List<TTable>
)