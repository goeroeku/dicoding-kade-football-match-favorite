package ic.aiczone.cifbmatchapps.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by aic on 25/10/18.
 * Email goeroeku@gmail.com.
 */

@Parcelize
data class Team(

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null

) : Parcelable