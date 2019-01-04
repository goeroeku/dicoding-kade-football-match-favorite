package ic.aiczone.cifbmatchapps.utils

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by aic on 21/09/18.
 * Email goeroeku@gmail.com.
 */

@SuppressLint("SimpleDateFormat")
fun changeFormatDate(date: Date?): String? = with(date ?: Date()){
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}

@SuppressLint("SimpleDateFormat")
fun strToDate(strDate: String?, pattern: String = "yyyy-MM-dd"): Date {
    val format = SimpleDateFormat(pattern)
    val date = format.parse(strDate)

    return date
}
