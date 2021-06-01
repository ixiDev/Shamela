package ixidev.shamela.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ixidev.shamela.data.ShamelaRepository
import ixidev.shamela.data.entities.BookInfo
import ixidev.shamela.data.models.CategoryItemInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdelmajid ID ALI, on 01/06/2021
 * Github : [https://github.com/ixiDev]
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ShamelaRepository
) : ViewModel() {


    val categories: Flow<List<CategoryItemInfo>>
        get() = repository.getAllCategories()

    fun getCategoryBooks(categoryId: Long): Flow<List<BookInfo>> {
        return repository.getBooksByCategory(categoryId.toInt())
    }


}