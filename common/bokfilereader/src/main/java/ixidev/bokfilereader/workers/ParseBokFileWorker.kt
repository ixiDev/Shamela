package ixidev.bokfilereader.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import ixidev.bokfilereader.BokFileReader

/**
 * Created by Abdelmajid ID ALI, on 22/05/2021
 * Github : [https://github.com/ixiDev]
 */
class ParseBokFileWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            val bokFilePath = inputData.getString("bok_file") ?: error("bok_file cannot be null")
            val reader = BokFileReader(bokFilePath)
            val bokFile = reader.getBokFile()
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


    companion object {
        fun createInputData(bokFilePath: String) = workDataOf(
            "bok_file" to bokFilePath
        )
    }
}