package ic.aiczone.cifbmatchapps.activity.event

import com.google.gson.Gson
import ic.aiczone.cifbmatchapps.utils.ApiRepository
import ic.aiczone.cifbmatchapps.utils.ApiResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by aic on 21/09/18.
 * Email goeroeku@gmail.com.
 */

class EventPresenter(private val view: EventFragment,
                     private val api: String,
                     private val gson: Gson){

    fun getList(){
        view.showLoading()

        doAsync {
            val data = gson.fromJson(ApiRepository().doRequest(api), ApiResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showList(data.events)
            }
        }
    }

}