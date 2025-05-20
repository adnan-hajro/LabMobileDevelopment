package ibu.edu.ba.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "workouts",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String?,
    val duration: Int,
    val intensity: String,
    val status: String,
    val caloriesBurned: Int?,
    val date: Long,
    val categoryId: Int,
    val userId: Int
)