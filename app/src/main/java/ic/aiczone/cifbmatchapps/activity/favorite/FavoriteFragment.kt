package ic.aiczone.cifbmatchapps.activity.favorite

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ic.aiczone.cifbmatchapps.R
import ic.aiczone.cifbmatchapps.activity.detail.DetailActivity
import ic.aiczone.cifbmatchapps.adapters.FavoriteAdapter
import ic.aiczone.cifbmatchapps.entities.Favorite
import ic.aiczone.cifbmatchapps.utils.database
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteFragment : Fragment(), AnkoComponent<Context> {
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteAdapter(favorites) {
            ctx.startActivity<DetailActivity>("id" to "${it.eventId}")
        }
        listEvent.adapter = adapter

        showFavorite()

        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )

                listEvent = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}
