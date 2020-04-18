package com.example.gamebacklog.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gamebacklog.model.Game

@Dao
interface GameDao {
  @Insert
  suspend fun insertGame(note: Game)

  @Query("SELECT * FROM Game LIMIT 1")
  fun getGameBacklog(): LiveData<Game?>

  @Update
  suspend fun updateGameBacklog(note: Game)

  //TODO: add delete/ deleteAll function
}
