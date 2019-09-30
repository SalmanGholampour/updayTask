package com.upday.task.ui.main_page

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.upday.task.data.DataManager
import com.upday.task.data.model.image.*
import com.upday.task.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner
import java.net.SocketTimeoutException

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @Mock
    lateinit var dataManager: DataManager

    @Mock
    lateinit var mainNavigator: MainNavigator


    private lateinit var viewModel: MainViewModel
    private lateinit var mTestScheduler: TestScheduler
    private lateinit var compositeDisposable: CompositeDisposable
    @Before
    @Throws(Exception::class)
    fun setUp() {
        mTestScheduler = TestScheduler()
        compositeDisposable = CompositeDisposable()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)
        viewModel = MainViewModel(dataManager, testSchedulerProvider, compositeDisposable)
        viewModel.setNavigator(mainNavigator)
    }

    @Test
    fun getImage() {
        val imageResponse = getMockImageResponse()
        val imageRequest = getMockImageRequest()
        doReturn(Single.just(imageResponse))
            .`when`(dataManager)
            .getImages(imageRequest)

        viewModel.fetchImages(imageRequest)
        mTestScheduler.triggerActions()
        assertEquals(imageResponse.data[0], viewModel.imageDataObservableArrayList[0])
    }

    @Test
    fun testError() {
        val errorResponse = SocketTimeoutException()
        val imageRequest = getMockImageRequest()
        whenever(dataManager.getImages(imageRequest)).thenReturn(Single.error(errorResponse))


        viewModel.fetchImages(imageRequest)
        mTestScheduler.triggerActions()
        verify(mainNavigator, times(1)).showConnectionError()
    }


    @Test
    fun testAccessToken() {
        doReturn("")
            .`when`(dataManager).getAccessToken()


        val b: Boolean = viewModel.isAccessTokenExist()
        assertEquals(false, b)

    }

    private fun getMockImageResponse(): ImageResponse {
        val imagePreview = ImagePreview(300, 200, "someUrl")
        val largeThumb = LargeThumb(300, 200, "someUrl")
        val smallThumb = SmallThumb(300, 200, "someUrl")
        val hugeThumb = HugeThumb(300, 200, "someUrl")
        val preview1000 = Preview_1000(300, 200, "someUrl")
        val preview1500 = Preview_1500(300, 200, "someUrl")
        val asset = Asset(imagePreview, largeThumb, smallThumb, hugeThumb, preview1000, preview1500)
        val imageData = ImageData(1.5f, "someId", asset)
        val imageList = mutableListOf<ImageData>()
        imageList.add(imageData)
        return ImageResponse(imageList)
    }

    private fun getMockImageRequest(): ImageRequest {
        return ImageRequest("someToken", 20, 1)
    }

}