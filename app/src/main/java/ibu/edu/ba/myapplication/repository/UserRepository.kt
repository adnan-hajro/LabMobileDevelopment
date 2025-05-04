package ibu.edu.ba.myapplication.repository

import ibu.edu.ba.myapplication.model.User

interface UserRepository : BaseRepository<User> {
    suspend fun getUserByEmail(email: String): User?

    suspend fun getUserByEmailAndPassword(email: String, password: String): User?;
}