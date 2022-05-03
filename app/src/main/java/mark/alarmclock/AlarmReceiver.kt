package mark.alarmclock

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.util.Calendar

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val c: Calendar = Calendar.getInstance()
		c.setTimeInMillis(System.currentTimeMillis())
		val hour: Int = c.get(Calendar.HOUR_OF_DAY);
		Log.d("", System.currentTimeMillis().toString())
		Toast.makeText(context, "拎拎～～",
				Toast.LENGTH_LONG).show()

//		val am = context.getSystemService(Context.ALARM_SERVICE) as?
//				AlarmManager
//		val pi = Util.setPendingIntent(context)
//		Util.setNextAlarm(context, am, pi)
    }
}
