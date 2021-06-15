import com.example.data1.CountriesRepository
import com.example.usecases1.ToggleCountryFavorite
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ToggleCountryFavouriteTest {
    @Mock
    lateinit var countriesRepository: CountriesRepository

    lateinit var toggleCountryFavorite: ToggleCountryFavorite

    @Before
    fun setUp() {
        toggleCountryFavorite = ToggleCountryFavorite(countriesRepository)
    }

    @Test
    fun `invoke calls countries repository`() {
        runBlocking {
            val country = mockedCountry.copy(1)

            val result = toggleCountryFavorite.invoke(country)

            verify(countriesRepository).update(result)
        }
    }

    @Test
    fun `favorite country becomes unfavorite`() {
        runBlocking {
            val country = mockedCountry.copy(favourite = true)
            val result = toggleCountryFavorite.invoke(country)

            assertFalse(result.favourite)
        }
    }

    @Test
    fun `favorite country becomes favorite`() {
        runBlocking {
            val country = mockedCountry.copy(favourite = false)
            val result = toggleCountryFavorite.invoke(country)

            assertTrue(result.favourite)
        }
    }
}