package pl.edu.uwr.pum.lista3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class ListDetailsFragment : Fragment() {
    private lateinit var dbHandler : DBHandler
    private lateinit var listTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHandler = DBHandler(requireActivity())

        arguments?.let {
            listTitle = it.getString("listTitle").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var Rv: RecyclerView = view.findViewById(R.id.TasksRecyclerView)
        Rv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TasksAdapter(dbHandler, listTitle)
        }

        val addButton: Button = view.findViewById(R.id.addTask)
        val addInput: TextInputEditText = view.findViewById(R.id.taskInput)

        addButton.setOnClickListener {
            val task: String = addInput.text.toString()
            if (task.isNotEmpty()) {
                dbHandler.addTask(Task(task, listTitle))
                addInput.text?.clear()
            }
            Rv.adapter?.notifyItemInserted(dbHandler.getTasks(listTitle).size)
        }
    }

    override fun onDestroy() {
        dbHandler.close()
        super.onDestroy()
    }
}