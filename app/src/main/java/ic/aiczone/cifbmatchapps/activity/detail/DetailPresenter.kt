package ic.aiczone.cifbmatchapps.activity.detail

import com.google.gson.Gson
import ic.aiczone.cifbmatchapps.entities.MatchDetailResponse
import ic.aiczone.cifbmatchapps.entities.TeamResponse
import ic.aiczone.cifbmatchapps.utils.ApiRepository
import ic.aiczone.cifbmatchapps.utils.ApiResponse
import ic.aiczone.cifbmatchapps.utils.DBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by aic on 21/09/18.
 * Email goeroeku@gmail.com.
 */

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson){

    fun getEventDetail(eventId: String) {
        view.showLoading()

        doAsync{
            val data = gson.fromJson(apiRepository
                    .doRequest(DBApi(eventId).getMatchDetail()),
                    MatchDetailResponse::class.java
            )

            val homeBadge = gson.fromJson(apiRepository
                    .doRequest(DBApi(data.events[0].homeTeamId).getTeamDetail()),
                    TeamResponse::class.java
            )

            val awayBadge = gson.fromJson(apiRepository
                    .doRequest(DBApi(data.events[0].awayTeamId).getTeamDetail()),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetail(data.events, homeBadge.teams, awayBadge.teams)
            }
        }
    }

}