package com.example.gamebacklog.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepository = GameRepository(application.applicationContext)

    val game: LiveData<List<Game>> = gameRepository.getAllGames()

    fun deleteAllGames() {
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }

    fun insertGame(game: Game) {
        ioScope.launch {
            gameRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game) {
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }

}
