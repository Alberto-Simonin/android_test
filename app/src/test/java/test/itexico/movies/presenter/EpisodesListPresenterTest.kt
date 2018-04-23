package test.itexico.movies.presenter

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import test.itexico.movies.adapters.ListEpisodesAdapter
import test.itexico.movies.managers.ImageRequest
import test.itexico.movies.managers.RequestManager
import test.itexico.movies.model.objects.Episode

class EpisodesListPresenterTest {

    val mockEpisodesListPresenter = mock<EpisodesListPresenter>()

    @Before
    fun setUp() {
    }

    @Test
    fun setData() {
        val mockResponse = ArrayList<Episode>()
        val ep0 = "{\"season\":1,\"number\":2,\"title\":\"The Kingsroad\",\"ids\":{\"trakt\":73641,\"tvdb\":3436411,\"imdb\":\"tt1668746\",\"tmdb\":63057,\"tvrage\":1065023912},\"number_abs\":2,\"overview\":\"Having agreed to become the King’s Hand, Ned leaves Winterfell with daughters Sansa and Arya, while Catelyn stays behind in Winterfell. Jon Snow heads north to join the brotherhood of the Night’s Watch. Tyrion decides to forego the trip south with his family, instead joining Jon in the entourage heading to the Wall. Viserys bides his time in hopes of winning back the throne, while Daenerys focuses her attention on learning how to please her new husband, Drogo.\",\"rating\":8.09095,\"votes\":10236,\"comment_count\":3,\"first_aired\":\"2011-04-25T01:00:00.000Z\",\"updated_at\":\"2018-04-20T13:22:46.000Z\",\"available_translations\":[\"ar\",\"be\",\"bg\",\"bs\",\"cn\",\"cs\",\"da\",\"de\",\"el\",\"en\",\"eo\",\"es\",\"fa\",\"fi\",\"fr\",\"he\",\"hr\",\"hu\",\"id\",\"is\",\"it\",\"ko\",\"lb\",\"lt\",\"lv\",\"nl\",\"no\",\"pl\",\"pt\",\"ro\",\"ru\",\"sk\",\"sr\",\"sv\",\"th\",\"tr\",\"tw\",\"uk\",\"vi\",\"zh\"],\"runtime\":55}"
        mockResponse.add(Gson().fromJson(ep0, Episode::class.java))
        val mockRequestManager = mock<RequestManager>()
        val mockImageRequest = mock<ImageRequest>()

        doReturn(mock<Context>()).whenever(mockEpisodesListPresenter).getContext()
        doReturn(mock<RecyclerView>()).whenever(mockEpisodesListPresenter).getView()
        doReturn(mock<ListEpisodesAdapter>()).whenever(mockEpisodesListPresenter).getListEpisodesAdapter(mockResponse)
        doReturn(mockImageRequest).whenever(mockEpisodesListPresenter).getImageRequest(anyOrNull(), anyOrNull(), anyOrNull())
        doReturn(mockRequestManager).whenever(mockEpisodesListPresenter).getRequestManagerInstance()

        doCallRealMethod().whenever(mockEpisodesListPresenter).setData(mockResponse)
        mockEpisodesListPresenter.setData(mockResponse)

        inOrder(mockRequestManager).verify(mockRequestManager, calls(2)).addToRequestQueue(mockImageRequest)
    }
}