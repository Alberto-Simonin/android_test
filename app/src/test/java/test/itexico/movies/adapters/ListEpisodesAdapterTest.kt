package test.itexico.movies.adapters

import android.content.Context
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import test.itexico.movies.model.Episode

class ListEpisodesAdapterTest {

    private lateinit var testAdapter: ListEpisodesAdapter
    private lateinit var episodesList: ArrayList<Episode>
    @Mock
    val mockContext = mock<Context>()

    @Before
    fun setUp() {
        val ep0 = "{\"season\":1,\"number\":1,\"title\":\"Winter Is Coming\",\"ids\":{\"trakt\":73640,\"tvdb\":3254641,\"imdb\":\"tt1480055\",\"tmdb\":63056,\"tvrage\":1065008299},\"number_abs\":1,\"overview\":\"Ned Stark, Lord of Winterfell learns that his mentor, Jon Arryn, has died and that King Robert is on his way north to offer Ned Arryn’s position as the King’s Hand. Across the Narrow Sea in Pentos, Viserys Targaryen plans to wed his sister Daenerys to the nomadic Dothraki warrior leader, Khal Drogo to forge an alliance to take the throne.\",\"rating\":8.0856,\"votes\":13131,\"comment_count\":17,\"first_aired\":\"2011-04-18T01:00:00.000Z\",\"updated_at\":\"2018-04-20T13:20:58.000Z\",\"available_translations\":[\"ar\",\"be\",\"bg\",\"bs\",\"cn\",\"cs\",\"da\",\"de\",\"el\",\"en\",\"eo\",\"es\",\"fa\",\"fi\",\"fr\",\"he\",\"hr\",\"hu\",\"id\",\"is\",\"it\",\"ko\",\"lb\",\"lt\",\"lv\",\"nl\",\"no\",\"pl\",\"pt\",\"ro\",\"ru\",\"sk\",\"sr\",\"sv\",\"th\",\"tr\",\"tw\",\"uk\",\"vi\",\"zh\"],\"runtime\":61}"
        val ep1 = "{\"season\":1,\"number\":2,\"title\":\"The Kingsroad\",\"ids\":{\"trakt\":73641,\"tvdb\":3436411,\"imdb\":\"tt1668746\",\"tmdb\":63057,\"tvrage\":1065023912},\"number_abs\":2,\"overview\":\"Having agreed to become the King’s Hand, Ned leaves Winterfell with daughters Sansa and Arya, while Catelyn stays behind in Winterfell. Jon Snow heads north to join the brotherhood of the Night’s Watch. Tyrion decides to forego the trip south with his family, instead joining Jon in the entourage heading to the Wall. Viserys bides his time in hopes of winning back the throne, while Daenerys focuses her attention on learning how to please her new husband, Drogo.\",\"rating\":8.09095,\"votes\":10236,\"comment_count\":3,\"first_aired\":\"2011-04-25T01:00:00.000Z\",\"updated_at\":\"2018-04-20T13:22:46.000Z\",\"available_translations\":[\"ar\",\"be\",\"bg\",\"bs\",\"cn\",\"cs\",\"da\",\"de\",\"el\",\"en\",\"eo\",\"es\",\"fa\",\"fi\",\"fr\",\"he\",\"hr\",\"hu\",\"id\",\"is\",\"it\",\"ko\",\"lb\",\"lt\",\"lv\",\"nl\",\"no\",\"pl\",\"pt\",\"ro\",\"ru\",\"sk\",\"sr\",\"sv\",\"th\",\"tr\",\"tw\",\"uk\",\"vi\",\"zh\"],\"runtime\":55}"
        val gson = Gson()
        episodesList = ArrayList()
        episodesList.add(gson.fromJson(ep0, Episode::class.java))
        episodesList.add(gson.fromJson(ep1, Episode::class.java))
        testAdapter = ListEpisodesAdapter(mockContext, episodesList)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun createWithResponseFull() {
        Assert.assertEquals(2, testAdapter.itemCount)
    }

    @Test
    fun createWithResponseEmpty() {
        episodesList.clear()
        val testAdapterEmpty = ListEpisodesAdapter(mockContext, episodesList)
        Assert.assertEquals(0, testAdapterEmpty.itemCount)
    }

    @Test
    fun getItem() {
        Assert.assertEquals(episodesList[0] ,testAdapter.getItem(0))
    }

    @Test
    fun getItemId() {
        Assert.assertEquals(episodesList[0].number.toLong(), testAdapter.getItemId(0) )
    }

    @Test
    fun getItemCount() {
        Assert.assertEquals(episodesList.size, testAdapter.itemCount)
    }
}