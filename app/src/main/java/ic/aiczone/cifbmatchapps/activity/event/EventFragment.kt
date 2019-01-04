package ic.aiczone.cifbmatchapps.activity.event

/**
 * Created by aic on 21/09/18.
 * Email goeroeku@gmail.com.
 */

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import ic.aiczone.cifbmatchapps.R
import ic.aiczone.cifbmatchapps.activity.detail.DetailActivity
import ic.aiczone.cifbmatchapps.adapters.EventAdapter
import ic.aiczone.cifbmatchapps.entities.MatchDetail
import ic.aiczone.cifbmatchapps.utils.DBApi
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class EventFragment: android.support.v4.app.Fragment() {
    private var match: MutableList<MatchDetail> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var review: RecyclerView
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var adapter: EventAdapter
    private var isPrev: Boolean = true
    private var leagueId = "4328"

    companion object {
        fun newInstance(leagueId: String, isPrev: Boolean): EventFragment {
            val fragment = EventFragment()
            fragment.isPrev = isPrev
            fragment.leagueId = leagueId
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dbApi = DBApi(leagueId)
        val api = if(isPrev) dbApi.getPrevSchedule() else dbApi.getNextSchedule()
        val gson = Gson()
        presenter = EventPresenter(this, api, gson)
        adapter = EventAdapter(match){
            ctx.startActivity<DetailActivity>("id" to it.eventId)
        }
        review.adapter = adapter

        swipe.onRefresh {
            presenter.getList()
        }

        presenter.getList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }


    fun showLoading() {
        swipe.isRefreshing = true
    }

    fun hideLoading() {
        swipe.isRefreshing = false
    }

    fun showList(data: List<MatchDetail>) {
        hideLoading()
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    fun createView(ui: AnkoContext<Context>): View = with(ui){
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipe = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                review = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }

        }
    }
}