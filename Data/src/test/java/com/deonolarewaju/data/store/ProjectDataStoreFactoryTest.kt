import com.deonolarewaju.data.store.ProjectCacheDataStore
import com.deonolarewaju.data.store.ProjectCacheDataStoreTest
import com.deonolarewaju.data.store.ProjectDataStoreFactory
import com.deonolarewaju.data.store.ProjectRemoteDataStore
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectDataStoreFactoryTest {

    private val cacheStore = mock<ProjectCacheDataStore>()
    private val remoteStore = mock<ProjectRemoteDataStore>()
    private val factory = ProjectDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenProjectsNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }

}