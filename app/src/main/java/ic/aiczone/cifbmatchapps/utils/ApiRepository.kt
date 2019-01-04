package ic.aiczone.cifbmatchapps.utils

import java.net.URL


/**
 * Created by aic on 21/09/18.
 * Email goeroeku@gmail.com.
 */

class ApiRepository {
    fun doRequest(url: String) = URL(url).readText()
}