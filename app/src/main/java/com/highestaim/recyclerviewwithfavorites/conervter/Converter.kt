package com.highestaim.recyclerviewwithfavorites.conervter

import com.highestaim.recyclerviewwithfavorites.entity.FavoriteEntity
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel


object Converter {

    fun favEntitiesToModels(favoriteEntity: List<FavoriteEntity?>): List<CommentsModel> {
        val modelList = arrayListOf<CommentsModel>()

        favoriteEntity.forEach {
            modelList.add(CommentsModel(name = it?.name, email = it?.email, id = it?.id?.toInt()))
        }
        return modelList
    }

    fun favEntityToModel(commentsModel: CommentsModel): FavoriteEntity? {
        return commentsModel.name?.let {
            commentsModel.email?.let { it1 ->
                commentsModel.id?.let { it2 ->
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