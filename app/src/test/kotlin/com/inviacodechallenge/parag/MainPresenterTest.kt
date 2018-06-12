package com.inviacodechallenge.parag

import com.inviacodechallenge.parag.models.RepositoryViewHandler
import com.inviacodechallenge.parag.repositories.GithubRepository
import com.inviacodechallenge.parag.schedulers.ThreadScheduler
import com.inviacodechallenge.parag.ui.main.MainPresenter
import com.inviacodechallenge.parag.ui.main.MainView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var repository: GithubRepository
    private lateinit var scheduler: ThreadScheduler
    private lateinit var view: MainView
    private lateinit var pojoResults: RepositoryViewHandler

    @Before
    fun setup() {
        repository = Mockito.mock(GithubRepository::class.java)
        view = Mockito.mock(MainView::class.java)
        scheduler = Mockito.mock(ThreadScheduler::class.java)
        presenter = MainPresenter(repository, TestScheduler())
        presenter.attach(view)
        pojoResults = TestUtil.pojoResults()
    }

    @Test
    fun shouldDisplayPOJOResutls() {
        Mockito.`when`(repository.loadRepository(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Observable.just(pojoResults))
        presenter.loadRepository(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())

        Mockito.verify(view, Mockito.times(1)).displayRepositories(pojoResults)
    }

    @Test
    fun shouldDisplayErrorViewIfTheresNoJSONRetrievedFromBackend() {
        Mockito.`when`(repository.loadRepository(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Observable.error(Exception()))

        presenter.loadRepository(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())

        Mockito.verify(view, Mockito.times(1)).displayError()
    }
}