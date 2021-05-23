package ixidev.bokfilereader.workers

import net.sf.sevenzipjbinding.ISequentialOutStream
import net.sf.sevenzipjbinding.SevenZipException
import java.io.OutputStream

/**
 * Created by Abdelmajid ID ALI, on 22/05/2021
 * Github : [https://github.com/ixiDev]
 */
class SingleFileExtractCallBack(private val outputStream: OutputStream) : ISequentialOutStream {
    override fun write(data: ByteArray?): Int {
        if (data.isNullOrEmpty()) {
            throw SevenZipException("null data")
        }
        outputStream.write(data)
        outputStream.close()
        return data!!.size
    }
}

private fun ByteArray?.isNullOrEmpty(): Boolean {
    return (this == null) or (this?.size == 0)
}
