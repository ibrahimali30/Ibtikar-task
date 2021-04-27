package com.ibrahim.ibtikar_task.notes.presentation.view.activity


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ibrahim.ibtikar_task.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.add_new_note_fab),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.note_title),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("testTitle"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.note_body),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("TestDescription"), closeSoftKeyboard())

        val linearLayout = onView(
            allOf(
                withId(R.id.container_due_date),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        1
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        val materialButton = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton.perform(scrollTo(), click())

        val materialButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.btSaveNote), withText("save"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        1
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
