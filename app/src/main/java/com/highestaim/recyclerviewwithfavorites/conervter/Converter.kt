package com.highestaim.recyclerviewwithfavorites.conervter

import com.highestaim.recyclerviewwithfavorites.entity.CommonEntity
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

    fun commonEntitiesToComments(favoriteEntity: List<CommonEntity?>): List<CommentsModel> {
        val comments = arrayListOf<CommentsModel>()

        favoriteEntity.forEach {
            comments.add(CommentsModel(name = it?.name, email = it?.email, id = it?.id?.toInt()))
        }
        return comments
    }


    fun commentsToCommonEntities(comments: List<CommentsModel?>): List<CommonEntity> {
        val entities = arrayListOf<CommonEntity>()

        comments.forEach {
            entities.add(CommonEntity(name = it?.name, email = it?.email, id = it?.id?.toLong()))
        }
        return entities
    }
}