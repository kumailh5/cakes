package com.kumail.cakes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kumail.cakes.data.model.Cake
import com.kumail.cakes.data.model.ErrorResponse
import com.kumail.cakes.data.repository.CakesRepository
import com.kumail.cakes.network.ApiResponse
import com.kumail.cakes.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

/**
 * Created by kumailhussain on 16/11/2021.
 */
@Config(sdk = [30])
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var repository: CakesRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        viewModel = Mockito.spy(MainViewModel(repository))
    }

    @Test
    fun `Verify cakes list being mapped on event`() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCakesList()).thenReturn(mockCakesResponse)
            viewModel = MainViewModel(repository)
            MatcherAssert.assertThat(
                mockFilteredCakes.data,
                `is`(CoreMatchers.equalTo(viewModel.cakesList.getOrAwaitValue()))
            )
        }
    }

    @Test
    fun `Verify error response mapped on event`() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCakesList())
                .thenReturn(mockErrorResponse as ApiResponse<List<Cake>>)
            viewModel = MainViewModel(repository)
            MatcherAssert.assertThat(
                mockErrorResponse.errorResponse.errorMessage,
                `is`(CoreMatchers.equalTo(viewModel.errorMessage.getOrAwaitValue()))
            )
        }
    }

    private val mockLemonCake =
        Cake(
            "Lemon cheesecake",
            "A cheesecake made of lemon",
            "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
        )

    private val mockBananaCake =
        Cake(
            "Banana cake",
            "Donkey kongs favourite",
            "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"
        )

    private val mockCakesResponse = ApiResponse.Success(
        listOf(mockLemonCake, mockLemonCake, mockBananaCake)
    )

    private val mockFilteredCakes = ApiResponse.Success(
        listOf(mockBananaCake, mockLemonCake)
    )
    
    private val mockErrorResponse =
        ApiResponse.NetworkError<ErrorResponse>(
            ErrorResponse(
                "Some error has occurred",
                "error",
                404
            )
        )
}