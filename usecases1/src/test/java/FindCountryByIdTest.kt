import com.example.data1.CountriesRepository
import com.example.usecases1.FindCountryById
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FindCountryByIdTest {

    @Mock
    lateinit var countriesRepository: CountriesRepository

    @Mock
    lateinit var findCountryById: FindCountryById

    @Before
    fun setUp() {
        findCountryById = FindCountryById(countriesRepository)
    }

    @Test
    fun `invoke calls countries repository`() {
        runBlocking {
            val country = mockedCountry.copy(1)
            whenever(countriesRepository.findById(1)).thenReturn(country)

            val result = findCountryById.invoke(1)

            assertEquals(country, result)
        }
    }

}