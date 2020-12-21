package cl.maleb.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM taskTable WHERE name LIKE '%' || :searchQuery || '%' ORDER BY important DESC")
    fun getTasks(searchQuery: String): Flow<List<Task>>

}