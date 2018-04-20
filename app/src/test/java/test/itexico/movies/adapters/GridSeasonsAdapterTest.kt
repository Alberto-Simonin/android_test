package test.itexico.movies.adapters

import android.content.Context
import com.google.gson.Gson
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import test.itexico.movies.model.Season

class GridSeasonsAdapterTest {

    private lateinit var testAdapter: GridSeasonsAdapter
    private lateinit var seasonsList: ArrayList<Season>
    @Mock
    val mockContext = mock(Context::class.java)

    @Before
    fun setUp() {
        val seas0 = "{\"number\":0,\"ids\":{\"trakt\":3962,\"tvdb\":137481,\"tmdb\":3627,\"tvrage\":{}},\"rating\":9.16144,\"votes\":223,\"episode_count\":40,\"aired_episodes\":34,\"title\":\"Specials\",\"overview\":\"\",\"first_aired\":\"2010-12-06T02:00:00.000Z\",\"network\":\"HBO\"}"
        val seas1 = "{\"number\":1,\"ids\":{\"trakt\":3963,\"tvdb\":364731,\"tmdb\":3624,\"tvrage\":{}},\"rating\":9.13506,\"votes\":2584,\"episode_count\":10,\"aired_episodes\":10,\"title\":\"Season 1\",\"overview\":\"Trouble is brewing in the Seven Kingdoms of Westeros. For the driven inhabitants of this visionary world, control of Westeros' Iron Throne holds the lure of great power. But in a land where the seasons can last a lifetime, winter is coming...and beyond the Great Wall that protects them, an ancient evil has returned. In Season One, the story centers on three primary areas: the Stark and the Lannister families, whose designs on controlling the throne threaten a tenuous peace; the dragon princess Daenerys, heir to the former dynasty, who waits just over the Narrow Sea with her malevolent brother Viserys; and the Great Wall--a massive barrier of ice where a forgotten danger is stirring.\",\"first_aired\":\"2011-04-18T01:00:00.000Z\",\"network\":\"HBO\"}"
        val gson = Gson()
        seasonsList = ArrayList()
        seasonsList.add(gson.fromJson(seas0, Season::class.java))
        seasonsList.add(gson.fromJson(seas1, Season::class.java))
        testAdapter = GridSeasonsAdapter(mockContext, seasonsList)
    }

    @Test
    fun createWithResponseFull() {
        Assert.assertEquals(2, testAdapter.itemCount)
    }

    @Test
    fun createWithResponseEmpty() {
        seasonsList.clear()
        val testAdapterEmpty = GridSeasonsAdapter(mockContext, seasonsList)
        Assert.assertEquals(0, testAdapterEmpty.itemCount)
    }

    @After
    fun tearDown() {
        seasonsList.clear()
    }

    @Test
    fun getItemId() {
        Assert.assertEquals(seasonsList[0].number.toLong(), testAdapter.getItemId(0) )
    }

    @Test
    fun getItem() {
        Assert.assertEquals(seasonsList[0] ,testAdapter.getItem(0))
    }

    @Test
    fun getItemCount() {
        Assert.assertEquals(seasonsList.size, testAdapter.itemCount)
    }
}