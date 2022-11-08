package pl.edu.uwr.pum.lista1

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Cheat : AppCompatActivity() {

    private val questionText: TextView by lazy{findViewById(R.id.question)}
    private val answerText: TextView by lazy{findViewById(R.id.answer)}
    private val buttonSearch: TextView by lazy{findViewById(R.id.searchOnlineButton)}

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val bundle = intent.extras

        if (bundle != null) {
            questionText.text = bundle.getString("question")

            if (bundle.getBoolean("correctAnswer"))
                answerText.text = "Odpowiedź: TAK"
            else
                answerText.text = "Odpowiedź: NIE"

            buttonSearch.setOnClickListener{searchOnline()}
        }
    }

    private fun searchOnline() {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, questionText.text.toString())
        startActivity(intent)
    }
}