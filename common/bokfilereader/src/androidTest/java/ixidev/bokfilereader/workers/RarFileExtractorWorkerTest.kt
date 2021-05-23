package ixidev.bokfilereader.workers

import android.content.Context
import android.os.FileUtils
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import ixidev.bokfilereader.workers.RarFileExtractorWorker
import ixidev.bokfilereader.workers.getBokFilePath
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.FileOutputStream

/**
 * Created by Abdelmajid ID ALI, on 22/05/2021
 * Github : [https://github.com/ixiDev]
 */
@RunWith(AndroidJUnit4::class)
class RarFileExtractorWorkerTest {
    private lateinit var context: Context


    @Before
    fun initContext() {
        context = ApplicationProvider.getApplicationContext()
    }


    @Test
    fun testExtractBokFile() = runBlocking {
        val bookId = 151016
        val rarFile = copyBookRarFromAssets(context, bookId)
        val extractFileWorker = TestListenableWorkerBuilder<RarFileExtractorWorker>(context)
            .setInputData(
                RarFileExtractorWorker.createDataInput(rarFile = rarFile.path)
            ).build()
        val result = extractFileWorker.doWork()
        assert(result is ListenableWorker.Result.Success)
        val outputData = (result as ListenableWorker.Result.Success).outputData

        val bokFilePath = outputData.getBokFilePath()
        assertFalse(bokFilePath.isNullOrEmpty())

    }

    private fun copyBookRarFromAssets(
        appContext: Context,
        bookId: Int
    ): File {
        val inputStream = appContext.assets.open("$bookId.rar")
        val rarFile = File(appContext.cacheDir, "$bookId.rar")
        if (!rarFile.exists())
            FileUtils.copy(inputStream, FileOutputStream(rarFile))
        return rarFile
    }
}