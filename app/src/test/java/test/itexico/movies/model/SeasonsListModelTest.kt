package test.itexico.movies.model

import android.app.Application
import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import com.android.volley.Response
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import test.itexico.movies.managers.RequestManager
import test.itexico.movies.managers.StandardRequest

class SeasonsListModelTest() {

    val mockApplication = mock<Application>()
    val mockSeasonsListModel = mock<SeasonsListModel>()
    val mockObservableSeasons = mock<MediatorLiveData<ArrayList<Season>>>()
    val mockErrorListener = mock<Response.ErrorListener>()


    @Before
    fun setUp() {
        val mockContext = mock<Context>()
        doReturn(mockContext).whenever(mockApplication).applicationContext
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getData_withNetworkAvailable() {
        val mockRequestManager = mock<RequestManager>()
        val mockStandardRequest = mock<StandardRequest>()

        doReturn(true).whenever(mockSeasonsListModel).networkIsAvailable()
        doReturn(mockStandardRequest).whenever(mockSeasonsListModel).getStandardRequest(ArgumentMatchers.anyString())
        doReturn(mockRequestManager).whenever(mockSeasonsListModel).getRequestManagerInstance()

        doCallRealMethod().whenever(mockSeasonsListModel).getData()
        mockSeasonsListModel.getData()

        verify(mockRequestManager).addToRequestQueue(mockStandardRequest)
    }

    @Test
    fun getData_withNetworkUnavailable() {

        var mockSeasonList = ArrayList<Season>()
        mockSeasonList.add(mock())
        mockSeasonList.add(mock())

        doReturn(false).whenever(mockSeasonsListModel).networkIsAvailable()
        doReturn(mockSeasonList).whenever(mockSeasonsListModel).getSeasonsInfoFromDB()
        doReturn(mockSeasonList).whenever(mockSeasonsListModel).getSeasonsArayList()
        doReturn(mockObservableSeasons).whenever(mockSeasonsListModel).getObservableSeasonsList()

        doCallRealMethod().whenever(mockSeasonsListModel).getData()
        mockSeasonsListModel.getData()

        verify(mockObservableSeasons).postValue(mockSeasonList)

    }

    @Test
    fun getData_withNetworkAvailableAndNoDataInCache() {
        val mockSeasonList = mock<ArrayList<Season>>()

        doReturn(false).whenever(mockSeasonsListModel).networkIsAvailable()
        doReturn(mockSeasonList).whenever(mockSeasonsListModel).getSeasonsInfoFromDB()
        doReturn(mockSeasonList).whenever(mockSeasonsListModel).getSeasonsArayList()
        doReturn(mockErrorListener).whenever(mockSeasonsListModel).getErrorListenerReference()
        doReturn("Test Error Message").whenever(mockSeasonsListModel).getErrorMessage()

        doCallRealMethod().whenever(mockSeasonsListModel).getData()
        mockSeasonsListModel.getData()

        verify(mockSeasonsListModel).getErrorListenerReference()
    }

}