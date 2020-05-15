package com.example.gamebacklog.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.adapters.GameAdapter
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.viewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

const val ADD_GAME_REQUEST_CODE = 100
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var games: ArrayList<Game>
    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startAddActivity()
        }

        recyclerView = findViewById(R.id.rvGames)

        games = arrayListOf()

        gameAdapter = GameAdapter(games)
        viewManager = LinearLayoutManager(this)
        createItemTouchHelper().attachToRecyclerView(recyclerView)

        initViewModel()

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = gameAdapter
        }

    }

    private fun initViewModel() {
        mainActivityViewModel.game.observe(this, Observer { games ->
            println(games)
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_GAME_REQUEST_CODE -> {
                    data?.let { safeData ->
                        val game = safeData.getParcelableExtra<Game>(NEW_GAME)
                        println(game)
                        game?.let { saveGame ->
                            println(saveGame)
                            mainActivityViewModel.insertGame(saveGame)
                        } ?: run {
                            Log.e(TAG, "reminder is null")
                        }
                    } ?: run {
                        Log.e(TAG, "empty intent data received")
                    }
                }
            }
        }
    }

    private fun startAddActivity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, ADD_GAME_REQUEST_CODE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.drawable.ic_delete -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]

                mainActivityViewModel.deleteGame(gameToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }
}
