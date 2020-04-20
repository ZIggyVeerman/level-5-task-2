package com.example.gamebacklog.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gamebacklog.model.Game

@Dao
interface GameDao {
  @Insert
  suspend fun insertGame(game: Game)

  @Query("SELECT * FROM Game")
  fun getAllGames(): LiveData<Game?>

  @Update
  suspend fun updateGameBacklog(game: Game)

  @Delete
  suspend fun deleteGame(game: Game)

  @Query("DELETE FROM Game")
  suspend fun deleteAllGames()
}
