package ixidev.shamela.data.workers

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker.Result
import androidx.work.testing.TestListenableWorkerBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/**
 * Created by Abdelmajid ID ALI, on 22/05/2021
 * Github : [https://github.com/ixiDev]
 */
private const val TAG = "FileDownloaderWorkerTes"

@RunWith(AndroidJUnit4::class)
class FileDownloaderWorkerTest {

    private lateinit var context: Context

    @Before
    fun initContext() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testDownloadFile() = runBlocking {
        val bookId = 151016

        val input = FileDownloaderWorker.createInputs(
            "$bookId.rar",
            "https://shamela.ws/books/1510/$bookId.rar"
        )


        val worker = TestListenableWorkerBuilder<FileDownloaderWorker>(context)
            .setWorkerFactory(FileDownloaderWorker.Factory())
            .setInputData(input)
            .build()

        val result = worker.doWork()

        assert(result is Result.Success)
        val outputData = (result as Result.Success).outputData
        val filepath = outputData.getString("rarFile")
        assertFalse(filepath.isNullOrEmpty())
        val file = File(filepath!!)
        assert(file.exists())
    }

}