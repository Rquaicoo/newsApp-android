package com.example.newsapp_android.bottom_tab

import com.example.newsapp_android.R

interface BottomTabDestination {
    val route : String
    val name: String
    val icon: Int
    val id: Int
}

object Home : BottomTabDestination {
    override val route = "home"
    override val name = "Home"
    override val icon = R.drawable.news_page_icon
    override val id = 1
}

object Search : BottomTabDestination {
    override val route = "search"
    override val name = "Search"
    override val icon = R.drawable.search
    override val id = 2
}

object Bookmarks : BottomTabDestination {
    override val route = "bookmarks"
    override val name = "Bookmarks"
    override val icon = R.drawable.bookmarks
    override val id = 3
}

object Profile : BottomTabDestination {
    override val route = "profile"
    override val name = "Profile"
    override val icon = R.drawable.profile
    override val id = 4
}