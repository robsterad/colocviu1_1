package ro.pub.cs.systems.eim.colocviu1_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01PrepSecondaryActivity: AppCompatActivity() {
    private lateinit var registerButton : Button
    private lateinit var cancelButton : Button
    private lateinit var show : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_colocviu1_1_secondary)

        registerButton = findViewById<Button>(R.id.register)
        cancelButton = findViewById<Button>(R.id.cancel)
        show = findViewById<TextView>(R.id.show)

        val intentFromParent = intent
        val data = intentFromParent.extras

        show.setText(data!!.getString("text"))

        registerButton.setOnClickListener {
            val returnIntent = Intent()
            setResult(RESULT_OK, null)
            finish()
        }
        cancelButton.setOnClickListener {
            val returnIntent = Intent()
            setResult(RESULT_CANCELED, null)
            finish()
        }
    }
}