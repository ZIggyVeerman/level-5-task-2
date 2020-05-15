package com.example.gamebacklog.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gamebacklog.dao.GameDao
import com.example.gamebacklog.database.GameBacklogRoomDatabase
import com.example.gamebacklog.model.Game

class GameRepository(context: Context) {

    private var gameDao: GameDao?

    init {
        val database = GameBacklogRoomDatabase.getDatabase(context)
        gameDao = database?.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao?.getAllGames() ?: MutableLiveData(emptyList())
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
