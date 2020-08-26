package com.arepadeobiri.arepadeobiri


import android.graphics.Color
import android.view.View
import com.arepadeobiri.arepadeobiri.dataModels.CarOwner
import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import com.google.android.material.snackbar.Snackbar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.*
import java.nio.charset.Charset
import java.util.*
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



        // Returns Snackbars
        fun getSnackBar(view: View, text: String) {

            Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()

        }




        //Filters car owners based on filter parameters passed and the list of Car Owners passed from CSV
        fun getFilteredCarOwners(filterItem: FilterItem, list: List<CarOwner?>?): List<CarOwner?>? {
            val filteredList = mutableListOf<CarOwner?>()

            list?.forEach {

                if ((!it?.gender.isNullOrBlank() && it?.gender?.toLowerCase(Locale.getDefault()) == filterItem.gender?.toLowerCase(
                        Locale.getDefault()
                    ))
                    && (it?.carModelYear!!.toInt() >= filterItem.startYear!!.toInt() && it.carModelYear.toInt() <= filterItem.endYear!!.toInt())
                    && (filterItem.countries?.find { country ->
                        country?.toLowerCase(Locale.getDefault()) == it.country.toLowerCase(
                            Locale.getDefault()
                        )
                    } != null)
                    && (filterItem.colors?.find { colors ->
                        colors?.toLowerCase(Locale.getDefault()) == it.color.toLowerCase(
                            Locale.getDefault()
                        )
                    } != null)
                ) {
                    filteredList.add(it)
                }

            }


            return filteredList
        }



        val CHART_COLORS = intArrayOf(
            Color.rgb(244, 67, 54),
            Color.rgb(103, 58, 183),
            Color.rgb(63, 81, 181),
            Color.rgb(76, 175, 80),
            Color.rgb(255, 193, 7),
            Color.rgb(0, 150, 136),
            Color.rgb(233, 30, 99),
            Color.rgb(33, 150, 243),
            Color.rgb(213, 29, 29),
            Color.rgb(205, 220, 57),
            Color.rgb(156, 39, 176),
            Color.rgb(233, 118, 30),
            Color.parseColor("#Ae2a9a"),
            Color.parseColor("#2aae30"),
            Color.parseColor("#3d2aae"),
            Color.parseColor("#644e03"),
            Color.parseColor("#024d54"),
            Color.parseColor("#F20865"),
            Color.parseColor("#F2be08"),
            Color.parseColor("#C2c408"),
            Color.parseColor("#F3c576"),
            Color.parseColor("#Ff0b05"),
            Color.parseColor("#1c0264"),
            Color.parseColor("#C70b87")
        )
    }
}


