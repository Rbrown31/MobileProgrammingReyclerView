package com.example.listmaker.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.listmaker.MainActivity
import com.example.listmaker.R
import com.example.listmaker.TaskList
import com.example.listmaker.databinding.ListDetailActivityBinding
import com.example.listmaker.ui.detail.ui.detail.ListDetailFragment
import com.example.listmaker.ui.detail.ui.detail.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {
    //lateinit var list: TaskList
    lateinit var binding: ListDetailActivityBinding
    lateinit var viewModel: ListDetailViewModel
    lateinit var fragment: ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }

        viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        // 1
        //list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        // 2
        title = viewModel.list.name
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.detail_container, ListDetailFragment.newInstance()).commitNow()
        }
    }

    private fun showCreateTaskDialog() {
        //1
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        //2
        AlertDialog.Builder(this).setTitle(R.string.task_to_add).setView(taskEditText).setPositiveButton(R.string.add_task) {
                dialog, _ -> val task = taskEditText.text.toString()
                viewModel.addTask(task)
                dialog.dismiss()
            }
        //6
            .create()
            .show()
    }
}
