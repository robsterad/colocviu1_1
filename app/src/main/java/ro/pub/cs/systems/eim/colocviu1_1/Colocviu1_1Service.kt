package ro.pub.cs.systems.eim.colocviu1_1

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class Colocviu1_1Service : Service() {
    private var processingThread: ProcessingThread? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val message = intent?.getStringExtra("message") ?: ""

        processingThread = ProcessingThread(this, message)
        processingThread?.start()

        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        processingThread?.stopThread()
        super.onDestroy()
    }
}

class ProcessingThread(private val context: Context, private val message : String) : Thread() {
    private var isRunning = true
    private val actions = listOf(
        "ro.pub.cs.systems.eim.action1",
        "ro.pub.cs.systems.eim.action2",
        "ro.pub.cs.systems.eim.action3"
    )

    override fun run() {
        try {
            sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val date = java.util.Date().toString()

        val intent = Intent()
        intent.action = actions.random()
        intent.putExtra("message", "Time: $date, Message: $message")
        context.sendBroadcast(intent)
    }

    fun stopThread() {
        isRunning = false
    }
}