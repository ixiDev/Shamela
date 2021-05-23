package ixidev.bokfilereader.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.sf.sevenzipjbinding.IInArchive
import net.sf.sevenzipjbinding.SevenZip
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream
import java.io.File
import java.io.FileOutputStream
import java.io.RandomAccessFile

/**
 * Created by Abdelmajid ID ALI, on 22/05/2021
 * Github : [https://github.com/ixiDev]
 */
class RarFileExtractorWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            val rarFile = File(inputData.getString("rarFile")!!)
            val folder = inputData.getString("folder") ?: rarFile.parentFile!!.path
            val bokFile = extractFile(rarFile, File(folder))
            Result.success(
                workDataOf("bok_file" to bokFile.path)
            )
        } catch (e: Exception) {
            Result.failure(
                workDataOf("exception" to e.message)
            )
        }

    }

    private suspend fun extractFile(rarFile: File, folder: File): File =
        withContext(Dispatchers.IO) {
            val randomAccessFile = RandomAccessFile(rarFile, "r")
            val inStream = RandomAccessFileInStream(randomAccessFile)
            val inArchive: IInArchive = SevenZip.openInArchive(null, inStream)
            val fileName = "${rarFile.nameWithoutExtension}.bok"
            val bokFile = File(folder, fileName)
            inArchive.extractSlow(
                0, SingleFileExtractCallBack(
                    FileOutputStream(
                        bokFile
                    )
                )
            )
            inArchive.close()
            inStream.close()
            bokFile
        }

    companion object {

        fun createDataInput(
            rarFile: String,
            folder: String? = null,
        ) = workDataOf(
            "rarFile" to rarFile,
            "folder" to folder,
        )
    }
}

fun Data.getBokFilePath(): String? {
    return getString("bok_file")
}