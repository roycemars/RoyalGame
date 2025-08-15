package com.roycemars.royalgame.auto

import androidx.car.app.CarAppService
import androidx.car.app.Session

class SearchCarAppService : CarAppService() {
    override fun onCreateSession(): Session = SearchSession()
}

class SearchSession : Session() {
    override fun onCreateScreen(intent: android.content.Intent) =
        object : androidx.car.app.Screen(carContext) {
            override fun onGetTemplate(): androidx.car.app.model.Template {
                val list = androidx.car.app.model.ItemList.Builder()
                    .addItem(
                        androidx.car.app.model.Row.Builder().setTitle("Search clubs...").build()
                    ).build()
                return androidx.car.app.model.ListTemplate.Builder()
                    .setTitle("RoyalGame")
                    .setSingleList(list)
                    .build()
            }
        }
}
