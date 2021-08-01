package com.colemichaels.jetpackdemo.espresso

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.colemichaels.jetpackdemo.MainActivity
import com.colemichaels.jetpackdemo.R
import com.colemichaels.jetpackdemo.api.OkHttpProvider
import com.colemichaels.jetpackdemo.idlingResource.DataBindingIdlingResource
import com.colemichaels.jetpackdemo.utilities.FileHelper
import com.colemichaels.jetpackdemo.utilities.coroutines.EspressoIdlingResource
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule val activityScenarioRule = activityScenarioRule<MainActivity>()

    private lateinit var dataBindingIdlingResource: IdlingResource

    private val mockWebServer = MockWebServer()
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val okHttpResource = OkHttp3IdlingResource.create("okhttp", OkHttpProvider.getOkHttpClient())
    private val coroutineResource = EspressoIdlingResource.getIdlingResource()

    @Before
    fun setup() {
        mockWebServer.start(8080)
        dataBindingIdlingResource = DataBindingIdlingResource(activityScenarioRule)
        IdlingRegistry.getInstance().register(okHttpResource, coroutineResource, dataBindingIdlingResource)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(okHttpResource, coroutineResource, dataBindingIdlingResource)
    }

    @Test
    fun buttonClick_DisplayText() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileHelper.getTextFromResources(context, R.raw.mock_response_giantbomb))
            }
        }

        onView(withId(R.id.convertButton))
            .perform(click())

        onView(withId(R.id.textView))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }
}