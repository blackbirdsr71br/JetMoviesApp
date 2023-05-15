package com.example.jetmoviesapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.local.data.local.WatchListDao
import com.example.local.data.local.WatchListDatabase
import com.example.local.data.local.entities.MovieEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WatchListDatabaseTest : TestCase() {

    private lateinit var db: WatchListDatabase
    private lateinit var dao: WatchListDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WatchListDatabase::class.java).build()
        dao = db.watchListDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeWatchListMovie() = runBlocking {
        val movie = MovieEntity(
            movieId = 100000,
            title = "Prueba Test Unit",
            originalTitle = "Unit test Movie",
            posterPath = "Pagina Web",
            voteAverage = 85.0,
            runtime = 1,
            overview = "Pelicula Dedicada a los Unit test"
        )

        dao.insert(movie)

        val movies = dao.getMovieByIdFromLocal(10000)

        if (movies != null) {
            assert(movies == movie)
        }
    }
}
