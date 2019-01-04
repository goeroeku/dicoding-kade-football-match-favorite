package ic.aiczone.cifbmatchapps.activity.detail

import ic.aiczone.cifbmatchapps.entities.MatchDetail
import ic.aiczone.cifbmatchapps.entities.Team


/**
 * Created by aic on 21/09/18.
 * Email goeroeku@gmail.com.
 */

interface DetailView {

    fun showLoading()
    fun hideLoading()
    fun showDetail(data: List<MatchDetail>, home: List<Team>, away: List<Team>)

}