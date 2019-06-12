package com.epam.task5

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun changeText_sameActivity() {
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())

        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)))
    }

    @Test
    fun changeText_newActivity() {
        onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED),
                closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())

        onView(withId(R.id.showTextView)).check(matches(withText(STRING_TO_BE_TYPED)))
    }

    companion object {
        const val STRING_TO_BE_TYPED = "It writes this text by itself!"
    }
}