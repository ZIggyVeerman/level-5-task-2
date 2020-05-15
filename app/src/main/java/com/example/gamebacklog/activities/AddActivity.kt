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
import java.lang.Error
import java.text.SimpleDateFormat
import java.util.*

const val NEW_GAME = "NEW_GAME"

class AddActivity : AppCompatActivity() {
    private lateinit var gameTitle: String
    private lateinit var gamePlatform: String
    private lateinit var releaseDate: Date
    private val format = SimpleDateFormat("dd MM yyyy", Locale.GERMAN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener { saveGame() }
    }

    private fun saveGame() {

        if (checkFields()) {
            val game = Game(gameTitle, gamePlatform, releaseDate)

            val resultIntent = Intent()
            resultIntent.putExtra(NEW_GAME, game)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }
    }

    private fun checkFields(): Boolean {
        // standard field validation
        if (tifTitle.text.isNullOrBlank()) {
            customToast(Companion.TITLE)
            return false
        }
        this.gameTitle = tifTitle.text.toString()

        // standard field validation
        if (tifPlatform.text.isNullOrBlank()) {
            customToast(PLATFORM)
            return false
        }
        this.gamePlatform = tifPlatform.text.toString()

        // date validation
        if (tifDay.text.isNullOrBlank()) {
            customToast(DAY)
        }
        if (tifMonth.text.isNullOrBlank()) {
            customToast(MONTH)
        }
        if (tifYear.text.isNullOrBlank()) {
            customToast(YEAR)
        }
        val gameDay = tifDay.text.toString()
        val gameMonth = tifMonth.text.toString()
        val gameYear = tifYear.text.toString()
        
        try {
            val newDate: Date? = format.parse("$gameDay $gameMonth $gameYear")

            if (newDate != null) releaseDate = newDate
        }catch (error: Error){
            customToast("$ERROR$error")
            return false
        }
        return true
    }

    private fun customToast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
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

    companion object {
        const val TITLE = "Geef een titel"
        const val PLATFORM = "Geef een platform"
        const val DAY = "Geef een dag op"
        const val MONTH = "Geef een maand op"
        const val YEAR = "Geef een geldig jaar op"
        const val ERROR = "Error zie fout: "
    }
}
