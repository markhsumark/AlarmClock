package mark.alarmclock

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.SystemClock
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    var pendingintent: PendingIntent? = null
    var am: AlarmManager? = null
    lateinit var AC_date : Button
    lateinit var btn_open: Button
    lateinit var btn_close: Button
    lateinit var AC_time:Button

    lateinit var alarm_DateTime : IntArray

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarm_DateTime = IntArray(5)

//        val btn_start: Button = findViewById(R.id.btn_start)
//        btn_start.setOnClickListener { start() }
//        val btn_end: Button = findViewById(R.id.btn_stop)
//        btn_end.setOnClickListener { stop() }

        pendingintent = Util.setPendingIntent(this@MainActivity)
        am = getSystemService(Context.ALARM_SERVICE) as?
                AlarmManager?
        
        btn_open = findViewById(R.id.btn_open)
        btn_close = findViewById(R.id.btn_close)
        AC_date = findViewById(R.id.btn_set_alarm_date)
        AC_time = findViewById(R.id.btn_set_alarm_time)
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)
        var hour = c.get(Calendar.HOUR_OF_DAY)
        var min = c.get(Calendar.MINUTE)
        alarm_DateTime[0] = year
        alarm_DateTime[1] = month
        alarm_DateTime[2] = day
        alarm_DateTime[3] = hour
        alarm_DateTime[4] = min

        btn_open.setOnClickListener{openAlarmClock()}
        btn_close.setOnClickListener{closeAlarmClock()}
        AC_date.text = "${setDateFormat(year, month, day)}"
        AC_time.text = "${setTimeFormat(hour, min)}"
        AC_date.setOnClickListener{setACDate()}
        AC_time.setOnClickListener{setACTime()}
    }
    fun openAlarmClock(){
        var DT = Calendar.getInstance()
        DT.set(Calendar.YEAR ,alarm_DateTime[0])
        DT.set(Calendar.MONTH ,alarm_DateTime[1])
        DT.set(Calendar.DAY_OF_MONTH ,alarm_DateTime[2])
        DT.set(Calendar.HOUR_OF_DAY ,alarm_DateTime[3])
        DT.set(Calendar.MINUTE ,alarm_DateTime[4])
        DT.set(Calendar.SECOND, 0)
        Util.showToast(this, "開啟${AC_date.text} ${AC_time.text}的鬧鐘")
        Util.setNextAlarm(this, am, pendingintent, DT)
    }
    fun closeAlarmClock(){
        Util.showToast(this, "關閉${AC_date.text} ${AC_time.text}的鬧鐘")
        am?.cancel(pendingintent);
    }
    fun setACDate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        var DPD = DatePickerDialog(this, { _, year, month, day ->
            run {
                val format = "${setDateFormat(year, month, day)}"
                AC_date.text = format
                alarm_DateTime[0] = year
                alarm_DateTime[1] = month
                alarm_DateTime[2] = day
            }
        }, year, month, day)
        DPD.datePicker.minDate = c.timeInMillis
        DPD.show()

    }
    fun setACTime() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val min = c.get(Calendar.MINUTE)
        val is24View : Boolean = false
        var TPD = TimePickerDialog(this, { _, hour, min ->
            run {
                val format = "${setTimeFormat(hour, min)}"
                AC_time.text = format
                alarm_DateTime[3] = hour
                alarm_DateTime[4] = min
            }
        }, hour, min, is24View)
        TPD.show()

    }
//    fun start() {
//        Util.setNextAlarm(this, am, pendingintent)
//        Toast.makeText(this, "整點報時開始",
//            Toast.LENGTH_SHORT).show();
//    }
//
//    fun stop() {
//        am?.cancel(pendingintent);
//        Toast.makeText(this, "停止整點報時",
//            Toast.LENGTH_SHORT).show();
//    }
    private fun setDateFormat(year: Int, month: Int, day: Int): String {
        return "$year/${month + 1}/$day"
    }
    private fun setTimeFormat(hour: Int, min: Int): String {
        return "$hour:$min"
    }

}
