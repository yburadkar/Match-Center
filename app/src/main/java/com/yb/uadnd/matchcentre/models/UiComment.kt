package com.yb.uadnd.matchcentre.models

import com.yb.uadnd.matchcentre.domain.models.Comment

data class UiComment(
    val type: String? = null,
    val text: String? = null,
    val time: String? = null,
    val period: String? = null
) {

    companion object {
        fun from(comment: Comment): UiComment {
            return with(comment) {
                UiComment(type, this.comment, time, period)
            }
        }
    }
}

