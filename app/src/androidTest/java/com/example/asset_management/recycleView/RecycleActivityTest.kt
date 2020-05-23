package com.example.asset_management.recycleView

import androidx.test.core.app.ActivityScenario

import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.assertion.ViewAssertions.matches

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.asset_management.R
import com.example.asset_management.mainHub.MainHubActivity

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RecycleActivityTest{

//    @get: Rule
//    val activityRule = ActivityScenarioRule(RecycleActivity::class.java)

    @Test
    fun testRecycleView() {

        val activityScenario = ActivityScenario.launch(MainHubActivity::class.java)

        onView(withId(R.id.devices))
                .check(matches(atPosition(0, withText("Test Text"))));

    }

}