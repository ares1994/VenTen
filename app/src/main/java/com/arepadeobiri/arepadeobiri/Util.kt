package com.arepadeobiri.arepadeobiri


import com.arepadeobiri.arepadeobiri.dataModels.CarOwner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.*
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

class Util {
    companion object {


        //Provides Http Logs for debug builds
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val loggingLevel = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply { httpLoggingInterceptor.level = loggingLevel }
        }



        //Provides OkHttpClient for Network calls
        fun provideClient(
            httpLoggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build()
        }






        //Checks for CSV file in VenTen folder and returns it if present
        fun checkForCsv(mainDirectory: File?): File? {
            val tempDirectory = File(mainDirectory, "VenTen")



            return if (!tempDirectory.listFiles()
                    .isNullOrEmpty()
            ) tempDirectory.listFiles()!![0] else null
        }







        //Reads Info from car owners CSV file
        fun readCsvFile(file: File): List<CarOwner?>? {

            val list = mutableListOf<CarOwner>()
            val reader =
                BufferedReader(InputStreamReader(FileInputStream(file), Charset.forName("UTF-8")))


            try {

                val carOwnerData = reader.readLines().drop(1)
                carOwnerData.forEach {
                    val items = it.split(",")
                    list.add(
                        CarOwner(
                            items[0].toInt(),
                            items[1],
                            items[2],
                            items[3],
                            items[4],
                            items[5],
                            items[6],
                            items[7],
                            items[8],
                            items[9],
                            items[10]
                        )
                    )

//
                }

                return list

            } catch (t: Throwable) {
                t.printStackTrace()
                return null
            }
        }


    }
}


