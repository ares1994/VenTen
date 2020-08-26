package com.arepadeobiri.arepadeobiri

import com.arepadeobiri.arepadeobiri.dataModels.CarOwner
import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import org.junit.Assert
import org.junit.Test


class FilterTest {


    companion object {
        val TEST_LIST = listOf(
            CarOwner(id = 1, firstName = "Scot", lastName = "Hainning", email = "shainning0@so-net.ne.jp", country = "Thailand", carModel = "Lincoln", carModelYear = "1996", color = "Maroon", gender = "Male", job = "Staff Accountant III", bio = "Cras mi pede"),
            CarOwner(id=2, firstName="Vannie", lastName="Fitzer", email="vfitzer1@samsung.com", country="France", carModel="Chrysler", carModelYear="2005", color="Green", gender="Female", job="VP Quality Control", bio="Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque."),
            CarOwner(id=3, firstName="Sissy", lastName="Willbourne", email="swillbourne2@xinhuanet.com", country="Bolivia", carModel="Lexus", carModelYear="2004", color="Puce", gender="Female", job="Staff Accountant I", bio="Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio"),
            CarOwner(id=4, firstName="Sallyanne", lastName="Basso", email="sbasso3@devhub.com", country="Peru", carModel="Ford", carModelYear="1995", color="Indigo", gender="Female", job="Actuary", bio="Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor. Duis mattis egestas metus. Aenean fermentum. Donec ut mauris eget massa tempor convallis."),
            CarOwner(id=5, firstName="Gusty", lastName="Klemt", email="gklemt4@meetup.com", country="Argentina", carModel="Honda", carModelYear="2006", color="Aquamarine", gender="Female", job="Legal Assistant", bio="Pellentesque ultrices mattis odio. Donec vitae nisi.")
        )
    }




    @Test
    fun filterFunctionForFirstFilterItem() {

        Assert.assertTrue(Util.getFilteredCarOwners(FilterItem("Male", 1990, 1, listOf("Brazil", "Ireland", "Egypt", "Peru"),2009,
            listOf("Green", "Violet", "Yellow", "Blue")), TEST_LIST)!!.isEmpty())

    }


    @Test
    fun filterFunctionForSecondFilterItem() {

        Assert.assertTrue(Util.getFilteredCarOwners(FilterItem("Male", 1990, 1, listOf("Russia", "Portugal", "Vietnam", "Croatia", "Uganda", "Iran"),2010,
            listOf("Teal", "Maroon", "Red", "Orange")), TEST_LIST)!!.isEmpty())

    }

    @Test
    fun willContain() {

        Assert.assertTrue(Util.getFilteredCarOwners(FilterItem("Male", 1990, 1, listOf("Brazil", "Ireland", "Egypt", "Peru", "Thailand"),2009,
            listOf("Green", "Violet", "Yellow", "Blue","Maroon")), TEST_LIST)!!.contains(TEST_LIST[0]))

    }

}