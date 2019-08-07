package com.smartco.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import androidx.test.ext.junit.rules.activityScenarioRule
/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
class SelectAndScanInventoryTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun shouldSelectOneShopAndScanIventory() {
        // Context of the app under test.
        onView(withId(R.id.stock_button)).perform(click())
    }
}
