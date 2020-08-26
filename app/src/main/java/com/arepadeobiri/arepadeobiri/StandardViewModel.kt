package com.arepadeobiri.arepadeobiri

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import com.arepadeobiri.arepadeobiri.network.GoogleDrive
import com.arepadeobiri.arepadeobiri.network.VenTen
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*

class StandardViewModel(application: Application) : AndroidViewModel(application) {

    private val job = Job()

    private val scope = CoroutineScope(Dispatchers.Main + job)


    private val _csvFile = MutableLiveData<File>()
    val csvFile: LiveData<File> get() = _csvFile


    private val _filters = MutableLiveData<List<FilterItem?>?>()
    val filters: LiveData<List<FilterItem?>?> get() = _filters


    private val _failed = MutableLiveData<String>()
    val failed: LiveData<String> get() = _failed




    private val google =  Retrofit.Builder()
        .baseUrl("https://drive.google.com")
        .client(Util.provideClient(Util.provideHttpLoggingInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create(GoogleDrive::class.java)



    private val venTen =  Retrofit.Builder()
        .baseUrl("https://ven10.co")
        .client(Util.provideClient(Util.provideHttpLoggingInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create(VenTen::class.java)


    override fun onCleared() {
        super.onCleared()

        job.cancel()
    }




    //Calls suspend function requesting Filters from endpoint
    fun getFilters(){
        scope.launch {
            filtersFromVenten()
        }
    }





    private suspend fun filtersFromVenten(){
        withContext(Dispatchers.IO){
            try{
                val response = venTen.getFiltersAsync().await()

                if (response != null){
                    _filters.postValue(response)
                }

            }catch (t:Throwable){
                _failed.postValue("Failed to retreive filters from server")
            }
        }
    }





    //Calls suspend function requesting CSV Google Drive
    fun downloadCsv(directory: File?) {

        scope.launch {



            //Checks if CSV has been downloaded before and is present then retrieves it instead of making network request again
            val file = Util.checkForCsv(directory)

            if (file != null) {
                _csvFile.postValue(file)
                return@launch
            }
            actualCsvDownload(directory)
        }
    }


    private suspend fun actualCsvDownload(directory: File?) {


        withContext(Dispatchers.IO) {
            try {
                val response: ResponseBody = google.getCsvAsync().await()
                writeResponseBodyToDisk(response, directory)

            } catch (t: Throwable) {
                _failed.postValue("It failed")

            }
        }

    }


    //Gets CSV data as response body then writes to file and saves in VenTen folder
    private fun writeResponseBodyToDisk(body: ResponseBody, directory: File?): Boolean {
        return try {

            val actualDirectory = File(directory, "VenTen")





            if (!actualDirectory.exists()) {
                actualDirectory.mkdir()
            }


            val futureStudioIconFile =
                File(
                    actualDirectory,
                    "car_ownsers_data.csv"
                )
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                }
                outputStream.flush()
                _csvFile.postValue(futureStudioIconFile)


                true
            } catch (e: IOException) {
                false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            false
        }
    }



    fun setFailedToNull(){
        _failed.value = null
    }


}




