package ixidev.bokfilereader.workers

import android.content.Context
import androidx.work.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

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

    suspend fun HttpClient.downloadFile(file: OutputStream, fileUrl: String): Int {
        return withContext(Dispatchers.IO) {
            try {

                val response = call {
                    this.url(fileUrl)
                    method = HttpMethod.Get
                }.response

                val data = ByteArray(response.contentLength()!!.toInt())
                var offset = 0

                do {
                    val currentRead = response.content.readAvailable(data, offset, data.size)
                    offset += currentRead
//                val progress = (offset * 100f / data.size).roundToInt()
//                emit(DownloadResult.Progress(progress))
                } while (currentRead > 0)

                response.close()

                if (response.status.isSuccess()) {
                    file.write(data)
                    data.size
                } else {
//                emit(DownloadResult.Error("File not downloaded"))
                    -1
                }
            } catch (e: TimeoutCancellationException) {
//            emit(DownloadResult.Error("Connection timed out", e))
                -1
            } catch (t: Throwable) {
//            emit(DownloadResult.Error("Failed to connect"))
                -1
            }
        }
    }
}