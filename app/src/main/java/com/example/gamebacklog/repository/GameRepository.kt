package com.example.gamebacklog.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.dao.GameDao
import com.example.gamebacklog.database.GameBacklogRoomDatabase
import com.example.gamebacklog.model.Game

class GameRepository(context: Context) {

  private val gameDao: GameDao

  init {
    val database = GameBacklogRoomDatabase.getDatabase(context)
    gameDao = database!!.gameDao()
  }

  fun getGame(): LiveData<Game?>{
    return gameDao.getGameBacklog()
  }

  suspend fun updateGameBacklog(game: Game) {
    gameDao.updateGameBacklog(game)
  }

}
