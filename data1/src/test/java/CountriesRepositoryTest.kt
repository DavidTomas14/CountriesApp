import com.example.data1.CountriesRepository
import com.example.data1.LocalDataSource
import com.example.data1.RemoteDataSource
import com.example.domain.Country
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountriesRepositoryTest{
    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var countriesRepository: CountriesRepository

    @Before
    fun setUp() {
        countriesRepository = CountriesRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `getCountries gets from local data source first`(){
        runBlocking {
            val localCountries = listOf(mockedCountry.copy(1))
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getCountries()).thenReturn(localCountries)

            val result = countriesRepository.getCountries()
            assertEquals(localCountries,result)
        }
    }

    @Test
    fun `getCountries saves remote data to local`(){
        runBlocking {
            val remoteCountries = listOf(mockedCountry.copy(2))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getCountries()).thenReturn(remoteCountries)

            countriesRepository.getCountries()
            verify(localDataSource).saveCountries(remoteCountries)
        }
    }

    @Test
    fun `findById calls local data source`(){
        runBlocking {
            val country = mockedCountry.copy(2)
            whenever(localDataSource.findById(2)).thenReturn(country)

            val result = countriesRepository.findById(2)
            assertEquals(country,result)
        }
    }

    @Test
    fun `update updates local data source`(){
        runBlocking {
            val country = mockedCountry.copy(1)
            countriesRepository.update(country)
            verify(localDataSource).update(country)
        }
    }
}


private val mockedCountry = Country(
    0,
    "España",
    "ES",
    "España",
    "Madrid",
    47000000,
    "castellano",
    "Europa",
    "Suroeste Europa",
    1239834,
    "flagpath",
    true
)