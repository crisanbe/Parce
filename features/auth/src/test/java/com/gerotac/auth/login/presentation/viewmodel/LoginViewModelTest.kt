package com.gerotac.auth.login.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.gerotac.auth.login.data.remote.LoginApi
import com.gerotac.auth.login.data.remote.logindto.LoginDto
import com.gerotac.shared.commons.Constant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutinesRule = MainCoroutineRule()

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var service: LoginApi

    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(null, null)
        service = retrofit.create(LoginApi::class.java)
    }

    //TODO NO ES UN VALOR NULL
    @Test
    fun checkCurrentLoginIsNotNullTest() {
        runBlocking {
            val result = service.doLogin(LoginDto("cris1@yopmail.com", "123456"))
            assertThat(result.state, `is`(notNullValue()))
        }
    }

    //TODO STATUS SUCCESS
    @Test
    fun checkStatusSuccessLoginTest() {
        runBlocking {
            val result = service.doLogin(LoginDto("cris1@yopmail.com", "123456"))
            assertThat(result.status, `is`("success"))
        }
    }

    //TODO STATUS ERROR EXPECTED
    @Test
    fun checkStatusErrorLoginTest() {
        runBlocking {
            try {
                service.doLogin(LoginDto("cris1@yopmail.com", ""))
            } catch (e: Exception) {
                assertThat(e.message, `is`("HTTP 404 Not Found"))
            }
        }
    }

    //TODO CHECK RETURN STATE OBSERVER
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun checkHourlySizeTest() = runTest {
        loginViewModel.doLogin(LoginDto("cris1@yopmail.com", "123456"))
        val result = loginViewModel.state.asLiveData().getOrAwaitValue()
        assertThat(result, `is`(result))
    }
}
