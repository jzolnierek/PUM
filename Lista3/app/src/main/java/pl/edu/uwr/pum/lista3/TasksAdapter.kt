package pl.edu.uwr.pum.lista3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private val dbHandler: DBHandler, private val listTitle: String) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    inner class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var task: TextView = itemView.findViewById(R.id.task)
        var removeButton: Button = itemView.findViewById(R.id.removeTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_details_item, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val item = dbHandler.getTasks(listTitle)[position]
        holder.task.text = item.content
        holder.removeButton.setOnClickListener {
            dbHandler.deleteTask(item)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount)
        }
    }

    override fun getItemCount(): Int {
        return dbHandler.getTasks(listTitle).size
    }

}