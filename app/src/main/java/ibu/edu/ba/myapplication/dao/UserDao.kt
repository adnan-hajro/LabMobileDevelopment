package ibu.edu.ba.myapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ibu.edu.ba.myapplication.model.User

@Dao
interface UserDao : BaseDao<User>{
    @Query("SELECT * FROM users WHERE users.email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend  fun getUserByEmailAndPassword(email: String, password: String): User?

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?
}