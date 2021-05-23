package ixidev.shamela.data.filedownloader

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import java.io.OutputStream

/**
 * Created by Abdelmajid ID ALI, on 21/05/2021
 * Github : [https://github.com/ixiDev]
 */

//sealed class DownloadResult {
//    object Success : DownloadResult()
//
//    data class Error(val message: String, val cause: Exception? = null) : DownloadResult()
//
//    data class Progress(val progress: Int) : DownloadResult()
//}


//suspend fun HttpClient.downloadFile(file: OutputStream, fileUrl: String): Flow<DownloadResult> {
//    return flow {
//        try {
//            val response = call {
//                this.url(fileUrl)
//                method = HttpMethod.Get
//            }.response
//
//            val data = ByteArray(response.contentLength()!!.toInt())
//            var offset = 0
//
//            do {
//                val currentRead = response.content.readAvailable(data, offset, data.size)
//                offset += currentRead
//                val progress = (offset * 100f / data.size).roundToInt()
//                emit(DownloadResult.Progress(progress))
//            } while (currentRead > 0)
//
//            response.close()
//
//            if (response.status.isSuccess()) {
//                withContext(Dispatchers.IO) {
//                    file.write(data)
//                }
//                emit(DownloadResult.Success)
//            } else {
//                emit(DownloadResult.Error("File not downloaded"))
//            }
//        } catch (e: TimeoutCancellationException) {
//            emit(DownloadResult.Error("Connection timed out", e))
//        } catch (t: Throwable) {
//            emit(DownloadResult.Error("Failed to connect"))
//        }
//    }
//}


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
