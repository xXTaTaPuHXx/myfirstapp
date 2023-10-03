package space.galimov.myfirstapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

private val game: Game = GameImp()

class MainActivity : AppCompatActivity() {

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.fieldRv).apply {
            layoutManager = GridLayoutManager(context, game.field.size)
            adapter = FieldAdapter(game.field) { row, col ->
                if (game.act(row, col)) {
                    adapter?.notifyDataSetChanged()
                    if (game.isFinished) {
                        val winnerText =
                            game.winner?.let { "The winner is ${it.toMark()}" } ?: "Tie"
                        Toast.makeText(context, winnerText, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}