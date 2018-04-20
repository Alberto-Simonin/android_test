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

class EpisodesListModelTest {

    val mockApplication = mock<Application>()
    val mockEpisodesListModel = mock<EpisodesListModel>()
    val mockObservableEpisodes = mock<MediatorLiveData<ArrayList<Episode>>>()
    val mockErrorListener = mock<Response.ErrorListener>()

    @Before
    fun setUp() {
        val mockContext = mock<Context>()
        doReturn(mockContext).whenever(mockApplication).applicationContext
        doReturn(1).whenever(mockEpisodesListModel).seasonId
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getData_withNetworkAvailable() {
        val mockRequestManager = mock<RequestManager>()
        val mockStandardRequest = mock<StandardRequest>()

        doReturn(true).whenever(mockEpisodesListModel).networkIsAvailable()
        doReturn(mockStandardRequest).whenever(mockEpisodesListModel).getStandardRequest(ArgumentMatchers.anyString())
        doReturn(mockRequestManager).whenever(mockEpisodesListModel).getRequestManagerInstance()

        doCallRealMethod().whenever(mockEpisodesListModel).getData()
        mockEpisodesListModel.getData()

        verify(mockRequestManager).addToRequestQueue(mockStandardRequest)
    }

    @Test
    fun getData_withNetworkUnavailable() {
        var mockEpisodesList = ArrayList<Episode>()
        mockEpisodesList.add(mock())
        mockEpisodesList.add(mock())

        doReturn(false).whenever(mockEpisodesListModel).networkIsAvailable()
        doReturn(mockEpisodesList).whenever(mockEpisodesListModel).getEpisodesInfoFromDB()
        doReturn(mockEpisodesList).whenever(mockEpisodesListModel).getEpisodesArrayList()
        doReturn(mockObservableEpisodes).whenever(mockEpisodesListModel).getObservableEpisodesList()

        doCallRealMethod().whenever(mockEpisodesListModel).getData()
        mockEpisodesListModel.getData()

        verify(mockObservableEpisodes).postValue(mockEpisodesList)
    }

    @Test
    fun getData_withNetworkAvailableAndNoDataInCache() {
        val mockEpisodesList = mock<ArrayList<Episode>>()

        doReturn(false).whenever(mockEpisodesListModel).networkIsAvailable()
        doReturn(mockEpisodesList).whenever(mockEpisodesListModel).getEpisodesInfoFromDB()
        doReturn(mockEpisodesList).whenever(mockEpisodesListModel).getEpisodesArrayList()
        doReturn(mockErrorListener).whenever(mockEpisodesListModel).getErrorListenerReference()
        doReturn("Test Error Message").whenever(mockEpisodesListModel).getErrorMessage()

        doCallRealMethod().whenever(mockEpisodesListModel).getData()
        mockEpisodesListModel.getData()

        verify(mockEpisodesListModel).getErrorListenerReference()
    }
}