package com.example.plumaFitApp.ui.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.DatesResponse
import com.example.plumaFitApp.data.models.User
import com.example.plumaFitApp.ui.base.getOrAwaitValue
import com.example.plumaFitApp.ui.main.home.HomeViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeViewModelTest : TestCase() {

    private lateinit var  viewModel: HomeViewModel


    @Before
    public override fun setUp() {
        super.setUp()

        viewModel= HomeViewModel()
        viewModel.user = User(
            "39965187819",
            "Victor Padula",
            "vhpadula@usp.br",
            "seEuQuiserFalarComDeus2021#$",
            "29-07-2000",
            "183",
            "75",
            "90",
            "120"
        )
    }

    @Test
    fun testLoadDates() {
        val result = viewModel.loadDates(0).getOrAwaitValue{
        }
        assertEquals(Result.Success::class.simpleName, result::class.simpleName)

    }

    @Test
    fun testLoadDayMeasurementDetails() {
        val result = viewModel.loadDayMeasurementDetails("21-10-2021").getOrAwaitValue{
        }
        assertEquals(Result.Success::class.simpleName, result::class.simpleName)
    }

    @Test
    fun testSetCalendarData() {
      val result =  viewModel.setCalendarData(DatesResponse(listOf("21-10-2021")))
        assertEquals(kotlin.collections.mutableListOf<com.applandeo.materialcalendarview.EventDay>()::class.simpleName, result::class.simpleName)
    }

    @Test
    fun testRegisterMeasurement() {
        val result = viewModel.registerMeasurement("120","2","Antes do almoço", "Medi antes e comer").getOrAwaitValue{
        }
        assertEquals(Result.Success::class.simpleName, result::class.simpleName)
    }
}