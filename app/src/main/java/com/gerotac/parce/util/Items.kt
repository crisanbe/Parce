package com.gerotac.parce.util

import com.gerotac.parce.R

class Items(
    val title: Int,
    val text: Int,
    val image: Int,
) {
    companion object {
        fun get(): List<Items> {
            return listOf(
                Items(
                    title = R.string.Text_Tittle1,
                    text = R.string.Text_Content,
                    R.drawable.logo_parce_original
                ),
                Items(
                    title = R.string.Text_Content1,
                    text = R.string.Text_Tittle2,
                    R.drawable.logo_parce_slider_2
                ),
                Items(
                    title = R.string.Text_Content1,
                    text = R.string.Text_Tittle3,
                    R.drawable.logo_parce_slider_tres
                )
            )
        }
    }
}