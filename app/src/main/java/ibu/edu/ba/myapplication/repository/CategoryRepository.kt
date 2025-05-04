package ibu.edu.ba.myapplication.repository

import ibu.edu.ba.myapplication.model.Category

interface CategoryRepository: BaseRepository<Category> {
    suspend fun getCategoriesByUserId(userId: Int): List<Category>;
}