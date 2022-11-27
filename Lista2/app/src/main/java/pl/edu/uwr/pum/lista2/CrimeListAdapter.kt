package pl.edu.uwr.pum.lista2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class CrimeListAdapter : RecyclerView.Adapter<CrimeListAdapter.CrimeListViewHolder>() {

    private val crimes = Crimes.crimes

    class CrimeListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.crimeTitle)
        val solved: ImageView = view.findViewById(R.id.crimeSolved)
    }

    override fun getItemCount(): Int = crimes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeListViewHolder {
        return CrimeListViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.crime_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: CrimeListViewHolder, position: Int) {
        val item = crimes[position]
        holder.title.text = item.title
        if (item.solved)
            holder.solved.visibility = View.VISIBLE
        else
            holder.solved.visibility = View.INVISIBLE

        holder.title.setOnClickListener{
            val action = CrimeListFragmentDirections
                .actionCrimeListFragmentToCrimeDetailsFragment(
                    id = position
                )
            holder.view.findNavController().navigate(action)
        }
    }

}