package com.example.glicemapapp.ui.home

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.glicemapapp.R
import com.example.glicemapapp.data.models.User
import com.example.glicemapapp.ui.main.home.HomeViewModel
import com.example.glicemapapp.ui.main.settings.SettingsPersonalFragment
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    private lateinit var  viewModel: HomeViewModel
    private lateinit var navController :TestNavHostController
    private lateinit var scenario : FragmentScenario<SettingsPersonalFragment>


    @Before
    fun setUp() {


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

        scenario  = launchFragmentInContainer(themeResId = R.style.Theme_GlicemapApp)
        scenario.moveToState(Lifecycle.State.CREATED)

        navController = TestNavHostController(
                androidx.test.core.app.ApplicationProvider.getApplicationContext())

        scenario.onFragment {fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_activity_main)


            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }
    @Test
    fun testEventFragment() {

        scenario.onFragment {
            onView(withId(R.id.save_button)).perform(click())
            assertEquals(navController.currentDestination?.id,R.id.newMeasure)
        }

    }
}