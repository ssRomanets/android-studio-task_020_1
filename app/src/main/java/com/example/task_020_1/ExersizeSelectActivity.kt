package com.example.task_020_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

@SuppressLint("MissingInflatedId")
class ExersizeSelectActivity : AppCompatActivity() {

    val exercises = ExersizeDataBase.exercises
    private lateinit var currentExersize: Exersize

    private var intent: Intent? = null
    private val exersizeType = mutableListOf(
        "exersize",
        "eva_overhead_lifting",
        "girl_doing_yoga",
        "girl_running_on_treadmill",
        "goblet_squad",
        "lift",
        "male_boxer",
        "manual_push_ups",
        "push_up_this_is_happening",
        "pushups",
        "squat_james_smith"
    )

    private lateinit var toolbarMain: Toolbar
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exersize_select)

        init()

        intent = Intent(this@ExersizeSelectActivity, ExersizeActiveActivity::class.java)

        setSupportActionBar(toolbarMain)
        title = "Упражнения по фитнесу."

        //adapter Spinner
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, exersizeType)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterSpinner

        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position > 0)
                    {
                        currentExersize = exercises[position-1]
                        intent?.putExtra("currentExersize", currentExersize)
                        startActivity(intent)
                    }
                }
                override  fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinner.onItemSelectedListener = itemSelectedListener
    }

    fun init() {
        toolbarMain = findViewById(R.id.toolbarMain)
        spinner = findViewById(R.id.spinner)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain->{
                moveTaskToBack(true);
                exitProcess(-1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}