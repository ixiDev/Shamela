package ixidev.bokfilereader

import com.healthmarketscience.jackcess.Database
import com.healthmarketscience.jackcess.DatabaseBuilder
import ixidev.bokfilereader.tables.BTable
import ixidev.bokfilereader.tables.MainTable
import ixidev.bokfilereader.tables.TTable
import java.io.File
import java.nio.charset.Charset
/**
 * Created by Abdelmajid ID ALI, on 21/05/2021
 * Github : [https://github.com/ixiDev]
 */
class BokFileReader(path: String) {

    private var _database: Database? = null
    private val database: Database
        get() = _database!!

    init {
        val file = File(path)
        if (!file.exists())
            error("File with path '$path' not exist")
        _database = DatabaseBuilder.open(File(path))
        database.charset = Charset.forName("windows-1256")
    }

    fun getBokFile(): BokFile {
        val mainTable = readMainTable(database)
        val bTable = readBTable(database, mainTable)
        val tTable = readTTable(database, mainTable)
        return BokFile(
            mainTable,
            bTable,
            tTable
        )
    }

    private fun readTTable(database: Database, mainTable: MainTable): List<TTable> {
        val bTableName = "t${mainTable.BkId}"
        val table = database
            .getTable(bTableName)
            ?: error("${database.file.name} not contains table with Name '$bTableName' ")
        val map = table.iterator().asSequence().map { row ->
            TTable(
                id = row["id"].toString().toIntOrNull(),
                tit = row.getString("tit"),
                lvl = row["lvl"].toString().toIntOrNull(),
                sub = row["sub"].toString().toIntOrNull()
            )
        }
        return map.toList()
    }

    private fun readBTable(database: Database, mainTable: MainTable): List<BTable> {
        val bTableName = "b${mainTable.BkId}"
        val table = database
            .getTable(bTableName)
            ?: error("${database.file.name} not contains table with Name '$bTableName' ")
        val map = table.iterator().asSequence().map { row ->
            BTable(
                id = row["id"].toString().toIntOrNull(),
                nass = row.getString("nass"),
                part = row["part"].toString().toIntOrNull(),
                page = row["page"].toString().toIntOrNull()
            )
        }
        return map.toList()
    }

    private fun readMainTable(database: Database): MainTable {
        val table = database
            .getTable("Main")
            ?: error("${database.file.name} not contains table with Name 'Main' ")

        if (table.rowCount == 0)
            error("Table 'Main' empty")

        val row = table.nextRow

        return MainTable(
            BkId = row["BkId"].toString().toIntOrNull(),
            Bk = row.getString("Bk"),
            Betaka = row.getString("Betaka"),
            Inf = row.getString("Inf"),
            Auth = row.getString("Auth"),
            AuthInf = row.getString("AuthInf"),
            TafseerNam = row.getString("TafseerNam"),
            IslamShort = row["IslamShort"].toString().toIntOrNull(),
            oNum = row["oNum"].toString().toIntOrNull(),
            oVer = row["oVer"].toString().toIntOrNull(),
            seal = row.getString("seal"),
            oAuth = row["oAuth"].toString().toIntOrNull(),
            bVer = row["bVer"].toString().toIntOrNull(),
            Pdf = row["Pdf"].toString().toIntOrNull(),
            oAuthVer = row["oAuthVer"].toString().toIntOrNull(),
            verName = row.getString("verName"),
            cat = row.getString("cat"),
            Lng = row.getString("Lng"),
            HigriD = row.getString("HigriD"),
            AD = row["AD"].toString().toIntOrNull(),
            aSeal = row.getString("aSeal"),
            bLnk = row.getString("bLnk"),
            PdfCs = row["PdfCs"].toString().toIntOrNull(),
            ShrtCs = row["ShrtCs"].toString().toIntOrNull()
        )
    }
}