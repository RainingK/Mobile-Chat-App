package com.example.mobilechatapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInActivityTest {

	@get:Rule
	val activityScenarioRule = ActivityScenarioRule(LogIn::class.java)

	@Test
	fun logIn_EmptyFields_Error() {
		// Leave email and password fields empty
		onView(withId(R.id.btn_login)).perform(click())

		// Check if error messages are displayed
		onView(withId(R.id.edit_text_email)).check(matches(hasErrorText("Email cannot be empty")))
		onView(withId(R.id.edit_text_password)).check(matches(hasErrorText("Password cannot be empty")))
	}

	// Add more test cases as needed

}
