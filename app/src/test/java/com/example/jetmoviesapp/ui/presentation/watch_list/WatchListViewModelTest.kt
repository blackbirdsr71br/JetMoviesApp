package com.example.jetmoviesapp.ui.presentation.watch_list

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.compose.AsyncImagePainter
import com.example.jetmoviesapp.data.local.WatchListDao
import com.example.jetmoviesapp.data.local.WatchListDatabase
import com.example.jetmoviesapp.data.local.entities.MovieEntity
import junit.framework.TestCase
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WatchListDatabaseTest2 : TestCase() {

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
    fun getWatchListMovie() = runBlocking {
        val movies = dao.getWatchList()
        assert(movies.filterNotNull().equals(""))
    }
}
