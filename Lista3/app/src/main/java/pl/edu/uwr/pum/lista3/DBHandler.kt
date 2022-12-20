package pl.edu.uwr.pum.lista3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION) {
    private companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "StudentHardLifeDB.db"
        private const val TABLE_TASKS = "TasksTable"
        private const val TABLE_LISTS = "ListsTable"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_TASK = "task"
        private const val COLUMN_LIST = "list"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TASKS_TABLE =
            "CREATE TABLE $TABLE_TASKS($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, $COLUMN_TASK TEXT, $COLUMN_LIST TEXT)"
        val CREATE_LISTS_TABLE =
            "CREATE TABLE $TABLE_LISTS($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, $COLUMN_LIST TEXT)"
        p0?.execSQL(CREATE_TASKS_TABLE)
        p0?.execSQL(CREATE_LISTS_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_LISTS")
        onCreate(p0)
    }

    fun addTask(task: Task) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TASK, task.content)
        contentValues.put(COLUMN_LIST, task.listTitle)
        db.insert(TABLE_TASKS, null, contentValues)
        db.close()
    }

    fun addList(title: String) {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_LISTS WHERE $COLUMN_LIST='$title'", null)
        if (!cursor.moveToFirst()) {
            val contentValues = ContentValues()
            contentValues.put(COLUMN_LIST, title)
            db.insert(TABLE_LISTS, null, contentValues)
        }

        db.close()
    }

    fun deleteTask(task: Task) {
        val db = this.writableDatabase
        db.delete(TABLE_TASKS,
        "$COLUMN_ID=${task.id}",
        null)
        db.close()
    }

    fun deleteList(list: MyList) {
        val db = this.writableDatabase
        db.delete(TABLE_LISTS, "$COLUMN_ID=${list.id}", null)
        db.delete(TABLE_TASKS, "$COLUMN_LIST='${list.title}'", null)
        db.close()
    }

    fun getTasks(listTitle: String) : List<Task> {
        val tasks : MutableList<Task> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_TASKS WHERE $COLUMN_LIST='$listTitle'", null)
        if (cursor.moveToFirst()) {
            do {
                tasks.add(Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2)))
            } while (cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return tasks
    }

    fun getLists() : List<MyList> {
        val lists: MutableList<MyList> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_LISTS", null)
        if (cursor.moveToFirst()) {
            do {
                lists.add(MyList(cursor.getInt(0), cursor.getString(1)))
            } while (cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return lists
    }

}

data class Task(val content: String, val listTitle: String) {
    var id: Int = 0

    constructor(id: Int, content: String, listTitle: String): this(content, listTitle) {
        this.id = id
    }
}

data class MyList(val title: String) {
    var id: Int = 0

    constructor(id: Int, title: String): this(title) {
        this.id = id
    }
}