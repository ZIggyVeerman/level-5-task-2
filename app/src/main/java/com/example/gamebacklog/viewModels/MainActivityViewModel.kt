package com.example.gamebacklog.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val game = gameRepository.getAllGames()

    fun deleteAllGames(){
        mainScope.launch {
            gameRepository.deleteAllGames()
        }
    }

    fun deleteGame(game: Game){
        mainScope.launch {
            gameRepository.deleteGame(game)
        }
    }

}
