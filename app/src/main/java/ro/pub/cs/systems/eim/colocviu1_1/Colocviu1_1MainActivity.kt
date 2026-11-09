package ro.pub.cs.systems.eim.colocviu1_1

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Colocviu1_1MainActivity : AppCompatActivity() {
    private lateinit var northButton : Button
    private lateinit var southButton : Button
    private lateinit var eastButton : Button
    private lateinit var westButton : Button

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
    }
}