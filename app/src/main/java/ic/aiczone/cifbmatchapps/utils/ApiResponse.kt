package ic.aiczone.cifbmatchapps.utils

import ic.aiczone.cifbmatchapps.entities.MatchDetail


/**
 * Created by aic on 21/09/18.
 * Email goeroeku@gmail.com.
 */

data class  ApiResponse(
        val events: List<MatchDetail>,
        val teams: List<MatchDetail>
)