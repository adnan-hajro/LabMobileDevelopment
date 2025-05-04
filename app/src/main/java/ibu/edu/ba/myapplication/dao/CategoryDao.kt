package ibu.edu.ba.myapplication.dao

import androidx.room.Dao
import androidx.room.Query
import ibu.edu.ba.myapplication.model.Category

@Dao
interface CategoryDao: BaseDao<Category> {
    @Query("SELECT * FROM categories WHERE userId = :userId;")
    suspend fun getCategoriesByUserId(userId: Int): List<Category>
}