package ibu.edu.ba.myapplication.repository

import ibu.edu.ba.myapplication.dao.CategoryDao
import ibu.edu.ba.myapplication.model.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao): CategoryRepository {
    override suspend fun getCategoriesByUserId(userId: Int): List<Category> {
        return categoryDao.getCategoriesByUserId(userId)
    }

    override suspend fun insert(entity: Category) {
        categoryDao.insert(entity)
    }

    override suspend fun update(entity: Category) {
        categoryDao.update(entity)
    }

    override suspend fun delete(entity: Category) {
        categoryDao.delete(entity)
    }
}