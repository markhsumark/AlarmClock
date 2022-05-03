package mark.alarmclock

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ResetAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val am = context.getSystemService(Context.ALARM_SERVICE) as?
                AlarmManager
        val pi = Util.setPendingIntent(context)
//        Util.setNextAlarm(context, am, pi)
    }
}