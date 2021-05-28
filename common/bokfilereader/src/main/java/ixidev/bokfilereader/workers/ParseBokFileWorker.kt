package ixidev.bokfilereader.workers

import android.content.Context
import androidx.work.*
import ixidev.bokfilereader.BokFileReader
import ixidev.bokfilereader.db.BokFileDataBase
import ixidev.bokfilereader.db.BokFileRepository

/**
 * Created by Abdelmajid ID ALI, on 22/05/2021
 * Github : [https://github.com/ixiDev]
 */
class ParseBokFileWorker(
    appContext: Context,
    params: WorkerParameters,
    private val repository: BokFileRepository
) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            val bokFilePath = inputData.getString("bok_file") ?: error("bok_file cannot be null")
            val reader = BokFileReader(bokFilePath)
            val bokFile = reader.getBokFile()

            val pages = repository.insertBookPages(bokFile.bElements)
            if (pages != bokFile.bElements.size)
                error("Cannot insert all pages total ${bokFile.bElements.size} , inserted $pages")

            val summaries = repository.insertBookSummary(bokFile.tElements)

            if (summaries != bokFile.tElements.size)
                error("Cannot insert all summary total ${bokFile.tElements.size} , inserted $summaries")

            Result.success(
                workDataOf(
                    "book_id" to bokFile.main.BkId!!
                )
            )
        } catch (e: Exception) {
            Result.failure(
                workDataOf(
                    "exception" to e.message
                )
            )
        }
    }

    class Factory(private val database: BokFileDataBase) : WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return ParseBokFileWorker(
                appContext, workerParameters,
                BokFileRepository(database)
            )
        }

    }

    companion object {
        @Suppress("unused")
        fun createInputData(bokFilePath: String) = workDataOf(
            "bok_file" to bokFilePath
        )
    }
}