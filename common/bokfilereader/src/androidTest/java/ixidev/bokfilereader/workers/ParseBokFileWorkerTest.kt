package ixidev.bokfilereader.workers

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import ixidev.bokfilereader.BokFile
import ixidev.bokfilereader.db.BokFileDataBase
import ixidev.bokfilereader.workers.FileDownloaderWorker
import ixidev.bokfilereader.workers.ParseBokFileWorker
import ixidev.bokfilereader.workers.RarFileExtractorWorker
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Abdelmajid ID ALI, on 22/05/2021
 * Github : [https://github.com/ixiDev]
 */
@RunWith(AndroidJUnit4::class)
class ParseBokFileWorkerTest {
    private lateinit var context: Context
    private lateinit var databse: BokFileDataBase
    private val bookId = 11055

    @Before
    fun initContext() {
        context = ApplicationProvider.getApplicationContext()
        context.deleteDatabase("$bookId")
        databse = BokFileDataBase.create(context, bookId)
        databse.clearAllTables()
    }

    @Test
    fun parseBokFile() = runBlocking {

        val url = "https://shamela.ws/books/110/$bookId.rar"


        val input = FileDownloaderWorker.createInputs("$bookId.rar", url)
        val downloaderWorker = TestListenableWorkerBuilder<FileDownloaderWorker>(context)
            .setWorkerFactory(FileDownloaderWorker.Factory())
            .setInputData(input)
            .build()
        val downloadWorkerResult = downloaderWorker.doWork()
        assert(downloadWorkerResult is ListenableWorker.Result.Success)

        val extractFileWorker = TestListenableWorkerBuilder<RarFileExtractorWorker>(context)
            .setInputData((downloadWorkerResult as ListenableWorker.Result.Success).outputData)
            .build()

        val extractFileResult = extractFileWorker.doWork()
        assert(extractFileResult is ListenableWorker.Result.Success)

        val parseBokFileWorker = TestListenableWorkerBuilder<ParseBokFileWorker>(context)
            .setWorkerFactory(ParseBokFileWorker.Factory(databse))
            .setInputData((extractFileResult as ListenableWorker.Result.Success).outputData)
            .build()

        val parsFileResult = parseBokFileWorker.doWork()

        assert(parsFileResult is ListenableWorker.Result.Success)
        val outputData = (parsFileResult as ListenableWorker.Result.Success).outputData

        assert(outputData.getInt("book_id", -1) != -1)


    }

}