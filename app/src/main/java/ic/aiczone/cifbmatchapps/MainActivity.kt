package ic.aiczone.cifbmatchapps

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ic.aiczone.cifbmatchapps.R.id.*
import ic.aiczone.cifbmatchapps.activity.event.EventFragment
import ic.aiczone.cifbmatchapps.activity.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var savedInstanceState: Bundle? = null
    private  var leagueId = "4328" //EPL
    private var isPrev: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_button.setOnNavigationItemSelectedListener {
            item -> when(item.itemId){
            nav_prev -> {
                isPrev = true
                openFragment(EventFragment.newInstance(leagueId, isPrev))
                return@setOnNavigationItemSelectedListener true
            }
            nav_next -> {
                isPrev= false
                openFragment(EventFragment.newInstance(leagueId, isPrev))
                return@setOnNavigationItemSelectedListener true
            }
            nav_favorite -> {
                openFragment(FavoriteFragment())
                return@setOnNavigationItemSelectedListener true

            }
        }
            false
        }
        nav_button.selectedItemId = nav_prev
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            eng_premier-> {
                leagueId = "4328"
            }
            ger_bundes -> {
                leagueId ="4331"
            }
            ita_serie -> {
                leagueId="4332"
            }
            fre_league -> {
                leagueId="4334"
            }
            spa_leage -> {
                leagueId = "4335"
            }
        }
        if (nav_button.selectedItemId == nav_favorite) {
            nav_button.selectedItemId = nav_prev
        }else {
            openFragment(EventFragment.newInstance(leagueId, isPrev))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openFragment(fragment: Fragment){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
        }
    }
}