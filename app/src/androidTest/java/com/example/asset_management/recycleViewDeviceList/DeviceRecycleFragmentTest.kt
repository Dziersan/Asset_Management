package com.example.asset_management.recycleViewDeviceList

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.asset_management.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)


class DeviceRecycleFragmentTest{


    @Test
    fun testRecycleViewFragmentInView() {

        val activityScenario = ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun testIsListItemVisible() {
        val activityScenario = ActivityScenario.launch(DeviceRecycleActivity::class.java)

        onView(withId(R.id.devices)).perform(RecyclerViewActions
                .actionOnItemAtPosition<DeviceAdapter.ViewHolder>(23, click()))
//                .check(matches(withText("000024")))
//
//        onView(withId(R.id.inventoryNumber)).check(matches(withText("000024")))
    }
}