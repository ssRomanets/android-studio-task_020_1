package com.example.task_020_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

@SuppressLint("MissingInflatedId")
class ExersizeActiveActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var titleTV: TextView
    private lateinit var exerciseTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var startButtonBTN: Button
    private lateinit var toNextExersizeButtonBTN: Button
    private lateinit var timerTV: TextView
    private lateinit var imageViewIV: ImageView

    private lateinit var currentExersize: Exersize

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exersize_active)

        init()

        setSupportActionBar(toolbarMain)
        title = "Упражнение по фитнесу."

        currentExersize = intent.getSerializableExtra("currentExersize") as Exersize

        exerciseTV.text = currentExersize.name
        descriptionTV.text = currentExersize.descripton

        startButtonBTN.setOnClickListener{
            startExersize()
        }
        toNextExersizeButtonBTN.setOnClickListener{
            val intent = Intent(this, ExersizeSelectActivity::class.java)
            startActivity(intent)
        }
    }

    fun init() {
        toolbarMain = findViewById(R.id.toolbarMain)
        titleTV = findViewById(R.id.titleTV)
        exerciseTV = findViewById(R.id.exerciseTV)
        descriptionTV = findViewById(R.id.descriptionTV)
        startButtonBTN = findViewById(R.id.startButtonBTN)
        toNextExersizeButtonBTN = findViewById(R.id.toNextExersizeButtonBTN)
        timerTV = findViewById(R.id.timerTV)
        imageViewIV = findViewById(R.id.imageViewIV)
    }

    private fun startExersize() {
        titleTV.text = "Начало упражнения"
        startButtonBTN.isEnabled = false
        startButtonBTN.text = "Процесс упражнения"

        imageViewIV.setImageResource(currentExersize.gifImage)
        timerTV.text = formatTime(currentExersize.durationInSeconds)
        timer = object : CountDownTimer(currentExersize.durationInSeconds*1000L, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                timerTV.text = formatTime((millisUntilFinished/1000).toInt())
            }
            override fun onFinish() {
                timerTV.text = "Упражнение завершено"
                imageViewIV.visibility = View.VISIBLE
                toNextExersizeButtonBTN.isEnabled = true
                imageViewIV.setImageResource(0)
            }
        }.start()
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return  String.format("%02d:%02d", minutes, remainingSeconds)
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