package pl.edu.uwr.pum.lista3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class AllListsFragment : Fragment() {
    private lateinit var dbHandler : DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHandler = DBHandler(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_lists, container, false)
    }

    override fun onDestroy() {
        dbHandler.close()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var Rv: RecyclerView = view.findViewById(R.id.ListsRecyclerView)
        Rv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListsAdapter(dbHandler)
        }

        val addButton: Button = view.findViewById(R.id.addTask)
        val addInput: TextInputEditText = view.findViewById(R.id.listInput)

        addButton.setOnClickListener {
            val listTitle: String = addInput.text.toString()
            if (listTitle.isNotEmpty()) {
                dbHandler.addList(listTitle)
                addInput.text?.clear()
            }
            Rv.adapter?.notifyItemInserted(dbHandler.getLists().size)
        }
    }
}