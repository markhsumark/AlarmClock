package mark.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import org.intellij.lang.annotations.JdkConstants
import java.util.*

class Util {

    companion object {

        fun setPendingIntent(context: Context): PendingIntent {
            var intent: Intent = Intent()
            intent.setClass(context, AlarmReceiver::class.java)

            // context.sendBroadcast(intent)

            val pendingintent = PendingIntent.getBroadcast(
                    context, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT
                        or PendingIntent.FLAG_IMMUTABLE)
            return pendingintent
        }

        fun setNextAlarm(context: Context, am: AlarmManager?,
                         pi: PendingIntent?, DateTime: Calendar) {

//            showToast(context, DateTime.get(Calendar.YEAR).toString()+DateTime.get(Calendar.MONTH).toString()+DateTime.get(Calendar.DAY_OF_MONTH).toString()+ DateTime.get(Calendar.HOUR_OF_DAY).toString()+DateTime.get(Calendar.MINUTE).toString())


            if (Build.VERSION.SDK_INT >= 31) {
                if (am?.canScheduleExactAlarms() == true) {
                    val intent = Intent()
                    intent.setClass(context, MainActivity::class.java)
                    am.set(AlarmManager.RTC_WAKEUP,
                        DateTime.timeInMillis, pi);
//                    val showpi = PendingIntent.getActivity(context, 0,
//                        intent, PendingIntent.FLAG_IMMUTABLE)

//                    val info = AlarmManager.AlarmClockInfo(DateTime.timeInMillis, showpi)

//                    am?.setAlarmClock(info, pi)

                    /*
                    am?.setExact(
                        AlarmManager.RTC_WAKEUP,
                        c.timeInMillis, pi)
                     */
                } else {
//                    showToast(context, "ho1")
                    am?.set(AlarmManager.RTC_WAKEUP, DateTime.timeInMillis, pi)
                }
            } else {
                Log.d("", (System.currentTimeMillis()+1000).toString()+"/"+(DateTime.timeInMillis).toString())
//                showToast(context, "ho2")
                am?.setExact(
                    AlarmManager.RTC_WAKEUP,
                    DateTime.timeInMillis, pi)
            }

        }
        fun showToast(context: Context, text: String){
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}
