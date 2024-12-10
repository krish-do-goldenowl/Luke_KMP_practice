package com.app.note

sealed class Screen(val route: String) {
    data object HomeScreen : Screen(route = "home-screen")
    data object LoginScreen : Screen(route = "login-screen")

    fun withArgs(vararg args: String) : String {
        return  buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}