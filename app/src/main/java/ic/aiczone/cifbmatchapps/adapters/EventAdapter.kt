package ic.aiczone.cifbmatchapps.adapters

/**
 * Created by aic on 21/09/18.
 * Email goeroeku@gmail.com.
 */

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ic.aiczone.cifbmatchapps.R
import ic.aiczone.cifbmatchapps.entities.MatchDetail
import ic.aiczone.cifbmatchapps.utils.*
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.coroutines.onClick

class EventAdapter(private val event: List<MatchDetail>, private val listener: (MatchDetail) -> Unit): RecyclerView.Adapter<EventAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = event.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val matchDate: TextView = itemView.find(R.id.date)
        private val homeTeam: TextView = itemView.find(R.id.homeTeam)
        private val homeScore: TextView = itemView.find(R.id.homeScore)
        private val awayScore: TextView = itemView.find(R.id.awayScore)
        private val awayTeam: TextView = itemView.find(R.id.awayTeam)

        fun bindItem(schedule: MatchDetail, listener: (MatchDetail) -> Unit){
            val date = strToDate(schedule.eventDate)
            matchDate.text = changeFormatDate(date)

            homeTeam.text = schedule.homeTeam
            homeScore.text = schedule.homeScore

            awayTeam.text = schedule.awayTeam
            awayScore.text = schedule.awayScore

            itemView.onClick {
                listener(schedule)
            }
        }
    }

    class EventUI: AnkoComponent<ViewGroup>{

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            cardView {
                lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    margin = dip(4)
                    radius = 4f
                }

                verticalLayout {

                    textView(R.string.default_date){
                        id = R.id.date
                    }.lparams(width = wrapContent, height = wrapContent){
                        gravity = Gravity.CENTER
                        margin = dip(8)
                    }

                    relativeLayout {

                        textView(R.string.default_home_team){
                            id = R.id.homeTeam
                            textSize = 14f
                            textColor = Color.BLACK
                            gravity = Gravity.END
                        }.lparams(width = wrapContent, height = wrapContent){
                            leftOf(R.id.homeScore)
                            rightMargin = dip(10)
                        }

                        textView(R.string.default_score){
                            id = R.id.homeScore
                            textSize = 12f
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                        }.lparams(width = wrapContent, height = wrapContent){
                            leftOf(R.id.vs)
                        }

                        textView(R.string.vs){
                            id = R.id.vs
                            textSize = 10f
                        }.lparams(width = wrapContent, height = wrapContent){
                            centerInParent()
                            leftMargin = dip(6)
                            rightMargin = dip(6)
                        }

                        textView(R.string.default_score){
                            id = R.id.awayScore
                            textSize = 12f
                            gravity = Gravity.CENTER
                            textColor = Color.BLACK
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightOf(R.id.vs)
                        }

                        textView(R.string.default_away_team){
                            id = R.id.awayTeam
                            textSize = 14f
                            textColor = Color.BLACK
                            gravity = Gravity.START
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightOf(R.id.awayScore)
                            leftMargin = dip(10)
                        }

                    }.lparams(width = matchParent, height = wrapContent)

                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    bottomMargin = dip(8)
                }
            }
        }

    }
}