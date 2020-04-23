package com.highestaim.recyclerviewwithfavorites.conervter

import com.highestaim.recyclerviewwithfavorites.entity.FavoriteEntity
import com.highestaim.recyclerviewwithfavorites.model.Model


object Converter {

    fun favEntitiesToModels(favoriteEntity: List<FavoriteEntity?>): List<Model> {
        val modelList = arrayListOf<Model>()

        favoriteEntity.forEach {
            modelList.add(Model(name = it?.name, email = it?.email, id = it?.id?.toInt()))
        }
        return modelList
    }

    fun favEntityToModel(model: Model): FavoriteEntity? {
        return model.name?.let {
            model.email?.let { it1 ->
                model.id?.let { it2 ->
                    FavoriteEntity(
                        name = it,
                        email = it1,
                        modelId = it2,
                        id = it2.toLong()
                    )
                }
            }
        }
    }

}