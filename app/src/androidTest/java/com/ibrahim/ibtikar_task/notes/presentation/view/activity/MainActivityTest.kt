package com.ibrahim.ibtikar_task.notes.presentation.view.activity


import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.ibrahim.ibtikar_task.R
import com.ibrahim.ibtikar_task.notes.presentation.view.fragment.NoteDetailFragment
import com.ibrahim.ibtikar_task.utils.timeToFormattedString
import com.ibrahim.ibtikar_task.utils.timeToFormattedStringAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    val mainActivity by lazy {
        mActivityTestRule.activity
    }

    val fragment: NoteDetailFragment by lazy {
        val fragment = mainActivity.supportFragmentManager.fragments.find {
            it is NoteDetailFragment
        }
        fragment as NoteDetailFragment
    }

    val time = Calendar.getInstance().timeInMillis
    val testDescription = "testDescription$time" //unique title
    val testTitle = "testTitle$time" //unique title

    @Test
    fun mainActivityTest2() {


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
                withId(R.id.note_title_fragment),
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
        appCompatEditText.perform(replaceText(testTitle), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.note_body_fragment),
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
        appCompatEditText2.perform(replaceText(testDescription), closeSoftKeyboard())

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

        val time = fragment.dateAndTimePicker.calendar.timeInMillis
        val formatedTime = timeToFormattedString(time)
        val formatedTimeForAdapter = timeToFormattedStringAdapter(time)


        val textView = onView(
            allOf(
                withId(R.id.tv_date),
                withParent(
                    allOf(
                        withId(R.id.container_due_date),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText(formatedTime)))


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
        fragment.dateAndTimePicker.calendar = Calendar.getInstance()
        materialButton3.perform(click())




        val textView3 = onView(
            allOf(
                withId(R.id.note_title), withText(testTitle),
                withParent(withParent(withId(R.id.recycler_view))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText(testTitle)))

        val textView4 = onView(
            allOf(
                withId(R.id.note_body), withText(testDescription),
                withParent(withParent(withId(R.id.recycler_view))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText(testDescription)))


        val textView6 = onView(
            allOf(
                withId(R.id.note_timestamp),
                 withText(formatedTimeForAdapter),
                withParent(withParent(withId(R.id.recycler_view))),
                isDisplayed()
            )
        )
        textView6.check(matches(withText(formatedTimeForAdapter)))

        textView6.perform(click())


        textView.check(matches(withText(formatedTime)))
        appCompatEditText.check(matches(withText(testTitle)))
        appCompatEditText2.check(matches(withText(testDescription)))


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
