package com.pd.core.prefence

import android.content.Context
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class AppPreferenceImplTest {
    private val context = mockk<Context>(relaxed = true)
    private lateinit var appPreference: AppPreference
    private val timeStamp = System.currentTimeMillis()

    @Before
    fun setUp() {
        appPreference = AppPreferenceImpl(context)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `dataTimeStamp shouldbe set timeStamp`() {
        val dataTimeStampSlot = slot<Long>()
        every { appPreference.dataTimestamp = capture(dataTimeStampSlot) } answers { System.currentTimeMillis() }
        runTest { appPreference.dataTimestamp } shouldBe Unit
    }

    @Test
    fun `dataTimeStamp shouldbe provided timeStamp`() {
        every { appPreference.dataTimestamp } answers { timeStamp }
        runTest {
            appPreference.dataTimestamp shouldBe timeStamp
        }
    }

    @Test
    fun `isDataStaled shouldbe provided false`() {
        every { appPreference.dataTimestamp } answers { timeStamp }
        runTest {
            appPreference.isDataStaled shouldBe false
        }
    }

    @Test
    fun `isDataStaled shouldbe provided true`() {
        every { appPreference getProperty "timeSinceLastUpdateMillis" } propertyType Long::class answers { 1689349622L }
        runTest {
            appPreference.isDataStaled shouldBe true
        }
    }

    @Test
    fun `isDataEmpty shouldbe provided true`() {
        every { appPreference getProperty "timeSinceLastUpdateMillis" } propertyType Long::class answers { 0L }
        runTest {
            appPreference.isDataEmpty shouldBe true
        }
    }

    @Test
    fun `isDataEmpty shouldbe provided false`() {
        every { appPreference getProperty "timeSinceLastUpdateMillis" } propertyType Long::class answers { timeStamp }
        runTest {
            appPreference.isDataEmpty shouldBe false
        }
    }

}
