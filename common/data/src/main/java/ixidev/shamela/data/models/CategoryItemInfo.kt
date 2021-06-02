package ixidev.shamela.data.models

import androidx.room.Embedded
import ixidev.shamela.data.entities.Category

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */
data class CategoryItemInfo(
    @Embedded
    val category: Category,
    val count: Int
)