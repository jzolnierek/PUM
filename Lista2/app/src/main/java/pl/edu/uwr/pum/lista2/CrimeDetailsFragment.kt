package pl.edu.uwr.pum.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

val crimes = Crimes.crimes

class CrimeDetailsFragment : Fragment() {

    private var crimeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { crimeId = it.getInt("id") }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crime_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val crime = crimes[crimeId]
        view.findViewById<TextView>(R.id.detailsCrimeTitle).text = "Tytuł: " + crime.title
        view.findViewById<TextView>(R.id.detailsCrimeContent).text = "Treść: " + crime.content
        view.findViewById<TextView>(R.id.detailsCrimeStudentIndex).text = "Indeks studenta: " + crime.studentIndex.toString()
        if (crime.solved)
            view.findViewById<TextView>(R.id.detailsCrimeSolved).text = "Rozwiązane: TAK"
        else
            view.findViewById<TextView>(R.id.detailsCrimeSolved).text = "Rozwiązane: NIE"
    }

}