package ixidev.bokfilereader.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abdelmajid ID ALI, on 21/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Entity
data class MainTable(
    /**
     * Id
     */
    @PrimaryKey
    val BkId: Int? = null,
    /**
     * Title
     */
    val Bk: String? = null,
    /**
     * Release info [عن الكتاب]
     */
    val Betaka: String? = null,
    val Inf: String? = null,
    /**
     * Author name
     */
    val Auth: String? = null,
    /**
     * Author full bio
     */
    val AuthInf: String? = null,
    val TafseerNam: String? = null,
    val IslamShort: Int? = null,
    val oNum: Int? = null,
    val oVer: Int? = null,
    val seal: String? = null,
    val oAuth: Int? = null,
    val bVer: Int? = null,
    val Pdf: Int? = null,
    val oAuthVer: Int? = null,
    val verName: String? = null,
    val cat: String? = null,
    val Lng: String? = null,
    /**
     * Release date in Higri
     */
    val HigriD: String? = null,
    val AD: Int? = null,
    val aSeal: String? = null,
    val bLnk: String? = null,
    val PdfCs: Int? = null,
    val ShrtCs: Int? = null
)