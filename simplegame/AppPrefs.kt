package com.test.simplegame

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences


object AppPrefs {
    private lateinit var preferences: SharedPreferences

    fun setUpAppPrefs(activity: Activity){
        preferences= activity.getSharedPreferences("settings",Context.MODE_PRIVATE)
    }
    //##############################
    fun setNameOfLatestPlayer (nameOfLatestPlayer:String){
        preferences.edit().putString("NAME_OF_LATEST_PLAYER",nameOfLatestPlayer).apply()
    }
    fun getNameOfLatestPlayer(): String?{
        return preferences.getString("NAME_OF_LATEST_PLAYER","")
    }

    //##############################
    fun setNameOfBestPlayer (nameOfBestPlayer:String){
        preferences.edit().putString("NAME_OF_BEST_PLAYER",nameOfBestPlayer).apply()
    }
    fun getNameOfBestPlayer(): String?{
        return preferences.getString("NAME_OF_BEST_PLAYER","")
    }
    //##############################
    fun setScoreOfBestPlayer (scoreOfBestPlayer: Int){
        preferences.edit().putInt("SCORE_OF_BEST_PLAYER",scoreOfBestPlayer).apply()
    }
    fun getScoreOfBestPlayer(): Int{
        return preferences.getInt("SCORE_OF_BEST_PLAYER",0)
    }
    //##############################
    fun setNumberOfCounter (numberOfCounter: Int){
        preferences.edit().putInt("COUNTER",numberOfCounter).apply()
    }
    fun getNumberOfCounter (): Int{
        return preferences.getInt("COUNTER",0)
    }
    //##############################


}