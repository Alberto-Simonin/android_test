package test.itexico.movies.presenter

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test
import test.itexico.movies.model.Season

class SeasonsListPresenterTest {

    val mockSeasonsListPresenter = mock<SeasonsListPresenter>()
    var mockResponse = ArrayList<Season>()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun setData_withResponseFull() {
    }

    @Test
    fun setData_withResponseEmpty() {
    }

    @Test
    fun setData_withResponseNull() {
    }
}