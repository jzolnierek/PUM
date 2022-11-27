package pl.edu.uwr.pum.lista2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class Crime(
    val id: Int,
    val title: String,
    val content: String,
    val studentIndex: Int,
    val solved: Boolean
)

object Crimes {
    val crimes: List<Crime> = listOf(
        Crime(0, "Ucieczka", "Uciekł z wykładu.", 378481, true),
        Crime(1, "Skopiowanie kodu", "Skopiował kod ze Stack Overflow.", 373638, false),
        Crime(2, "Spóźnienie", "Spóźnił się na zajęcia.", 151910, true),
        Crime(3, "Ściąganie", "Ściągał na kolokwium od kolegi.", 147023, false),
        Crime(4, "Użył Wolfram Alpha", "Użył Wolfram Alpha do rozwiązania zadania.", 370889, false),
        Crime(5, "Ściąganie", "Ściągał na egzaminie z telefonu.", 221369, true),
        Crime(6, "Ucieczka", "Uciekł z wykładu.", 370907, false),
        Crime(7, "Ucieczka", "Uciekł z zajęć.", 322750, false),
        Crime(8, "Skopiowanie kodu", "Skopiował kod od kolegi.", 129842, true),
        Crime(9, "Skopiowanie kodu", "Skopiował kod od koleżanki.", 317534, true),
        Crime(10, "Ściąganie", "Ściągał na kolokwium z telefnu.", 307517, true),
        Crime(11, "Ściąganie", "Ściągał na kolokwium ze smartwatcha.", 319260, false),
        Crime(12, "Użył Wolfram Alpha", "Użył Wolfram Alpha do rozwiązania zadania.", 267281, true),
        Crime(13, "Ściąganie", "Ściągał na kolokwium z telefonu.", 397934, false),
        Crime(14, "Ucieczka", "Uciekł z wykładu.", 382038, true),
        Crime(15, "Ściąganie", "Ściągał na kolokwium z telefonu.", 382038, true),
        Crime(16, "Spóźnienie", "Spóźnił się na egzamin.", 107661, false),
        Crime(17, "Skopiowanie kodu", "Skopiował kod ze Stack Overflow.", 110942, true),
        Crime(18, "Ucieczka", "Uciekł z wykładu.", 216754, true),
        Crime(19, "Ściąganie", "Ściągał na kolokwium z telefonu.", 345701, false),
        Crime(20, "Użył Wolfram Alpha", "Użył Wolfram Alpha do rozwiązania zadania.", 237050, false)
    )
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}