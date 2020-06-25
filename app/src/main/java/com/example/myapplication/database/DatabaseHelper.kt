package com.example.myapplication.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.data.User


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserManager.db"
        private val TABLE_USER = "user"
        private val USER_ID = "user_id"
        private val USER_NAME = "user_name"
        private val USER_NICKNAME="user_nickname"
        private val USER_PASSWORD = "user_password"
    }

    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT," +USER_NICKNAME+ " TEXT,"
            + USER_PASSWORD + " TEXT" + ")")
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_TABLE)
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }

//    fun isInvalid(username: String): Boolean {
//        val db = this.writableDatabase
//        val cursor = sqLiteDatabase?.rawQuery("select * from ${TABLE_USER} where ${USER_NAME} = '${username}'", null)
//        // sẽ chọn all các MyDatabase.TBL_USER với điều kiện ${MyDatabase.US_USERNAME} = '${username}'
//        val count = cursor?.count
//        cursor?.close()
//        close()
//        return count != 0
//    }
    fun insertData(user: User):Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USER_NAME, user.name)
        contentValues.put(USER_NICKNAME,user.nickname)
        contentValues.put(USER_PASSWORD, user.password)
        val result=db.insert(TABLE_USER, null, contentValues)
        return result?.toDouble()!=0.0
    }

    fun listOfUserInfo(): ArrayList<User> {
        // khả năng là getuser
        val db = this.writableDatabase
        val res = db.rawQuery("select * from " + TABLE_USER, null)
        val useList = ArrayList<User>()
        while (res.moveToNext()) {
            var user = User()
            user.id = Integer.valueOf(res.getString(0))
            user.name = res.getString(1)
            user.nickname=res.getString(2)
            user.password = res.getString(3)
            useList.add(user)
        }
        return useList
    }


    fun getAllUserData(): ArrayList<User> {
        val stuList: ArrayList<User> = arrayListOf<User>()
        val cursor: Cursor = getReadableDatabase().query(
            TABLE_USER,
            arrayOf(USER_ID, USER_NAME, USER_PASSWORD),
            null,
            null,
            null,
            null,
            null
        )
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        val id: Int = cursor.getInt(cursor.getColumnIndex(USER_ID))
                        val name: String = cursor.getString(cursor.getColumnIndex(USER_NAME))
                        val password: String =
                            cursor.getString(cursor.getColumnIndex(USER_PASSWORD))
                        var user = User()
                        user.id = id
                        user.name = name

                        user.password = password
                        stuList.add(user)
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }

        return stuList
    }

    fun getParticularUserData(id: String): User {
        var user = User()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + USER_ID + " = '" + id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        user.id = cursor.getInt(cursor.getColumnIndex(USER_ID))
                        user.name = cursor.getString(cursor.getColumnIndex(USER_NAME))
                        user.password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD))
                    } while ((cursor.moveToNext()));
                }
            }
        } finally {
            cursor.close();
        }
        return user
    }

    fun updateData(id: String, name: String, password: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USER_ID, id)
        contentValues.put(USER_NAME, name)

        contentValues.put(USER_PASSWORD, password)
        db.update(TABLE_USER, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    /**
     * delete the userData
     */
    fun deleteData(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_USER, "ID = ?", arrayOf(id))

    }
}

