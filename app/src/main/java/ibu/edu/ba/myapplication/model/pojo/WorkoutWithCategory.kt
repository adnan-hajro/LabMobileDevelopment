package ibu.edu.ba.myapplication.model.pojo

import androidx.room.Embedded
import androidx.room.Relation
import ibu.edu.ba.myapplication.model.Category
import ibu.edu.ba.myapplication.model.Workout

data class WorkoutWithCategory(
    @Embedded
    val workout: Workout,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: Category
)
