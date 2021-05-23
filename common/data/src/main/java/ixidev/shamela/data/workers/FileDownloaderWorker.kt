package ixidev.shamela.data.workers

import android.content.Context
import androidx.work.*
import androidx.work.Logger
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.forms.*
import io.ktor.util.*
import ixidev.shamela.data.filedownloader.downloadFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Created by Abdelmajid ID ALI, on 22/05/2021
 * Github : [https://github.com/ixiDev]
 */
class FileDownloaderWorker(
    appContext: Context,
    private val params: WorkerParameters,
    private val httpClient: HttpClient
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            val fileName = params.inputData.getString("file_name") ?: error(
                "file_name must not be null"
            )
            val url = params.inputData.getString("file_url") ?: error(
                "file_url must not be null"
            )
            val outFile = File(applicationContext.cacheDir, fileName)
            val fileOutputStream = FileOutputStream(outFile)
            val sizeDownloaded = httpClient.downloadFile(fileOutputStream, url)
            if (sizeDownloaded.toLong() == outFile.length())
                Result.success(
                    workDataOf(
                        "rarFile" to outFile.path
                    )
                )
            else error("File not downloaded successfully , data size $sizeDownloaded")
        } catch (e: Exception) {
            return Result.failure(
                workDataOf(
                    "exception" to e.message
                )
            )
        }
    }

    companion object {

        fun createInputs(fileName: String, url: String) =
            workDataOf(
                "file_name" to fileName,
                "file_url" to url
            )

    }


    class Factory : WorkerFactory() {
        @KtorExperimentalAPI
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return FileDownloaderWorker(
                appContext, workerParameters,
                HttpClient(Android) {
                    install(Logging) {
                        logger = io.ktor.client.features.logging.Logger.ANDROID
                        level = LogLevel.ALL
                    }
                }
            )
        }
    }
}