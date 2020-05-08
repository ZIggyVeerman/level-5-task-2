package com.example.gamebacklog.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import java.util.*

const val NEW_GAME = "NEW_GAME"

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            saveGame()
        }
    }

    private fun saveGame() {

        val gameTitle = tifTitle.text.toString()
        val gamePlatform = tifPlatform.text.toString()
        val gameDay = tifDay.text.toString()
        val gameMonth = tifMonth.text.toString()
        val gameYear = tifYear.text.toString()

        if(gameTitle.isNotBlank() and gamePlatform.isNotBlank()){
            if (gameDay.isNotBlank() and gameMonth.isNotBlank() and gameYear.isNotBlank()){
                println(gameDay)
            }
            val game = Game(gameTitle, gamePlatform, Date())

            val resultIntent = Intent()
            resultIntent.putExtra(NEW_GAME, game)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }else{
            Toast.makeText(this, "yoooo", Toast.LENGTH_SHORT).show()
        }

//        if (tifTitle.text == null) {
//            //toast text met vul title in
//        } else if (tifPlatform.text == null) {
//            //toast text met vul title in
//        } else if (tifDay.text == null || tifMonth.text == null || tifYear.text == null) {
//            // do stuff
//        } else {
//            var date = "${tifDay.text} + ${tifMonth.text} + ${tifYear.text}"
//
//            println(date)
//
//            addActivityViewModel.insertGame(
//                Game(
//                    tifTitle.text.toString(),
//                    tifPlatform.text.toString(),
//                    Date()
//                )
//            )
//            tifTitle.text?.clear()
//            tifPlatform.text?.clear()
//            tifDay.text?.clear()
//            tifMonth.text?.clear()
//            tifYear.text?.clear()
//        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return try {
            finish()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
