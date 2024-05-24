package com.hj.mylogin

enum class UserType {
    STUDENT {
        override fun getString() = "교수"
    },
    PROFESSOR {
        override fun getString() = "교수"
    },
    EMPLOYEE {
        override fun getString() = "교직원"
    };
    abstract fun getString(): String
}