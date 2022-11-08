package pl.edu.uwr.pum.lista1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import android.content.Intent
import java.util.*

class Question(
    val question: String,
    val correctAnswer: Boolean
)

object Questions {
    val questions: List<Question> = listOf(
        Question("Czy grawitacja na ziemi jest większa niż na księżycu?", true),
        Question("Czy siła jest wprost proporcjonalna do masy?", true),
        Question("Czy fala niebieska jest dłuższa niż czerwona? ", false),
        Question("Czy opór idealnego izolatora zalezy od temperatury?", false),
        Question("Czy w próżni cząsteczki poruszają się ruchem przyspieszonym?", false),
        Question("Czy tranzystor bipolarny posiada bramkę?", false),
        Question("Czy ciepło układu zamknięte się rozproszy?", false),
        Question("Czy pierwsza zasada dynamiki mówi o ruchu jednostajnym?", true),
        Question("Czy dźwięk jest fala elektromagnetyczna?", false),
        Question("Czy izolatory podlegają prawu Ohma?", false)
    )
}

class MainActivity : AppCompatActivity() {

    private val questions: List<Question> = Questions.questions
    private var currentQuestion: Int = 0
    private var points: Int = 0
    private var correctAnswersCount:Int = 0
    private var wrongAnswersCount:Int = 0
    private var cheatsCount:Int = 0

    private val questionText: TextView by lazy{findViewById(R.id.question)}
    private val buttonYes: TextView by lazy{findViewById(R.id.buttonYes)}
    private val buttonNo: TextView by lazy{findViewById(R.id.buttonNo)}
    private val buttonCheat: TextView by lazy{findViewById(R.id.buttonCheat)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setQuestion()
        buttonYes.setOnClickListener{userAnswer(true)}
        buttonNo.setOnClickListener{userAnswer(false)}
        buttonCheat.setOnClickListener{cheat()}
    }

    private fun setQuestion() {
        questionText.text = questions[currentQuestion].question
    }

    private fun userAnswer(answer:Boolean) {
        if (answer == questions[currentQuestion].correctAnswer) {
            points += 10
            correctAnswersCount++
        } else wrongAnswersCount++

        currentQuestion++

        if (currentQuestion < 10)
            setQuestion()
        else
            summary()
    }

    private fun cheat() {
        points -= 15
        cheatsCount++

        val intent = Intent(this, Cheat::class.java)

        val bundle = Bundle()
        bundle.putString("question", questions[currentQuestion].question)
        bundle.putBoolean("correctAnswer", questions[currentQuestion].correctAnswer)

        intent.putExtras(bundle)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun summary() {
        buttonYes.visibility = View.GONE
        buttonNo.visibility = View.GONE
        buttonCheat.visibility = View.GONE
        questionText.text = "Zdobyte punkty: $points\nPoprawne odpowiedzi: $correctAnswersCount\nZłe odpowiedzi: $wrongAnswersCount\nOszustwa: $cheatsCount"
    }

}