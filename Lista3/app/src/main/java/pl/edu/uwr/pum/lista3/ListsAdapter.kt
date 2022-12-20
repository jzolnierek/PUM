package pl.edu.uwr.pum.lista3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ListsAdapter(private val dbHandler: DBHandler) : RecyclerView.Adapter<ListsAdapter.ListsViewHolder>() {

    inner class ListsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var listTitle: TextView = itemView.findViewById(R.id.listTitle)
        var removeButton: Button = itemView.findViewById(R.id.removeList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsAdapter.ListsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_lists_item, parent, false)
        return ListsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListsAdapter.ListsViewHolder, position: Int) {
        val item = dbHandler.getLists()[position]
        holder.listTitle.text = item.title
        holder.removeButton.setOnClickListener {
            dbHandler.deleteList(item)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount)
        }
        holder.listTitle.setOnClickListener {
            val action = AllListsFragmentDirections.actionAllListsFragment2ToListDetailsFragment(
                listTitle = item.title
            )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dbHandler.getLists().size
    }

}