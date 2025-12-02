package com.example.task_manager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    // getting taskDAO by this function, for further use case
    abstract fun getTaskDAO() : TaskDAO

    // creating Database by createDatabase method

    // we create in companion object, So that we don't have to create
    // instance or object of the class before using it!
    companion object {

        // function here
        fun createDatabase(context: Context): TaskDatabase {
            return Room.databaseBuilder(context, TaskDatabase::class.java, "task-database").fallbackToDestructiveMigration().build()
        }

        /**
        Why .fallbackToDestructiveMigration() ?

        Because:

        If you change schema → Room requires a migration

        If you don’t add migration, Room throws an exception.
        Since you're still developing and don’t need the old saved data, you can allow Room to:

        ✔ Delete old DB
        ✔ Create new DB
        ✔ Avoid crash

        Later (for production apps), we would create proper migrations.
         **/

    }
}