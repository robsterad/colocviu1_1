package ro.pub.cs.systems.eim.colocviu1_1

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Colocviu1_1MainActivity : AppCompatActivity() {
    private lateinit var northButton : Button
    private lateinit var southButton : Button
    private lateinit var eastButton : Button
    private lateinit var westButton : Button

    private lateinit var navigate : Button
    private lateinit var showText : TextView

    private lateinit var timesClickedText: TextView

    private var timesClicked : Int = 0
    private lateinit var startForResult: ActivityResultLauncher<Intent>

    private val messageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("Colocviu1_1Broadcast", intent?.getStringExtra("message") ?: "Empty")
        }
    }
    private var serviceStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        northButton = findViewById<Button>(R.id.northButton)
        southButton = findViewById<Button>(R.id.southButton)
        eastButton = findViewById<Button>(R.id.eastButton)
        westButton = findViewById<Button>(R.id.westButton)
        navigate = findViewById<Button>(R.id.navigateButton)

        showText = findViewById<TextView>(R.id.showText)
        timesClickedText = findViewById<TextView>(R.id.timesClickedText)

        northButton.setOnClickListener {
            showText.setText(showText.text.toString() + " " + "NORTH")
            timesClicked++
            timesClickedText.setText(timesClicked.toString())
            checkAndStartService()
        }
        southButton.setOnClickListener {
            showText.setText(showText.text.toString() + " " + "SOUTH")
            timesClicked++
            timesClickedText.setText(timesClicked.toString())
            checkAndStartService()
        }
        eastButton.setOnClickListener {
            showText.setText(showText.text.toString() + " " + "EAST")
            timesClicked++
            timesClickedText.setText(timesClicked.toString())
            checkAndStartService()
        }
        westButton.setOnClickListener {
            showText.setText(showText.text.toString() + " " + "WEST")
            timesClicked++
            timesClickedText.setText(timesClicked.toString())
            checkAndStartService()
        }

        timesClickedText.setText(timesClicked.toString())

        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(
                    this, "REGISTERED",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(
                    this, "CANCELLED",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        navigate.setOnClickListener {
            val intent = Intent(this, Colocviu1_1SecondaryActivity::class.java).apply {
                putExtra("text", showText.text.toString())
            }
            startForResult.launch(intent)
        }
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)

        savedInstanceState.putInt("timesClicked", timesClicked)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        timesClicked = savedInstanceState.getInt("timesClicked")

    }
    private fun checkAndStartService() {
        if (timesClicked == 4) {
            val intent = Intent(this, Colocviu1_1Service::class.java)
            intent.putExtra("message", showText.text.toString())
            startService(intent)
            serviceStarted = true
        }
    }
    override fun onDestroy() {
        val intent = Intent(this, Colocviu1_1Service::class.java)
        stopService(intent)
        super.onDestroy()
    }
}