package com.example.gamebacklog.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.dao.GameDao
import com.example.gamebacklog.database.GameBacklogRoomDatabase
import com.example.gamebacklog.model.Game

class GameRepository(context: Context) {

  private val gameDao: GameDao?

  init {
    val database = GameBacklogRoomDatabase.getDatabase(context)
    gameDao = database?.gameDao()
  }
  //TODO: dit kan nooit goed zijn
  fun getAllGames(): LiveData<Game?>? {
    return gameDao?.getAllGames()
  }

  suspend fun insertGame(game: Game) {
    gameDao?.insertGame(game)
  }

  suspend fun updateGameBacklog(game: Game) {
    gameDao?.updateGameBacklog(game)
  }

  suspend fun deleteGame(game: Game) {
    gameDao?.deleteGame(game)
  }

  suspend fun deleteAllGames() {
    gameDao?.deleteAllGames()
  }
}
