package ixidev.shamela.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ixidev.shamela.data.ShamelaRepository
import ixidev.shamela.data.db.BookInfoDao
import ixidev.shamela.data.db.BooksDataBase

/**
 * Created by Abdelmajid ID ALI, on 31/05/2021
 * Github : [https://github.com/ixiDev]
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideBooksDataBase(@ApplicationContext context: Context): BooksDataBase {
        return BooksDataBase.create(context)
    }

    @Provides
    fun provideBookInfoDao(dataBase: BooksDataBase): BookInfoDao {
        return dataBase.bookInfoDao()
    }

    @Provides
    fun provideShamelaRepository(dataBase: BooksDataBase): ShamelaRepository {
        return ShamelaRepository(dataBase)
    }


}