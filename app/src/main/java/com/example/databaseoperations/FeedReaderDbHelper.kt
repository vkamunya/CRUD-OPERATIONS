package com.example.databaseoperations

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

object FeedReaderContract{
    //Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns{
        const val TABLE_NAME = "entry"
        const val COLUMN_ADMISSION_NUMBER = "admission"
        const val COLUMN_NAME = "name"
    }
}

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER_PRIMARY_KEY," +
            "${FeedReaderContract.FeedEntry.COLUMN_ADMISSION_NUMBER} TEXT,"+
            "${FeedReaderContract.FeedEntry.COLUMN_NAME} TEXT)"

private const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"

class FeedReaderDbHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
        Log.d("db", "Database created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion:Int, newVersion:Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion:Int, newVersion:Int) {
        onDowngrade(db,oldVersion, newVersion)
    }
    companion object{
        const val DATABASE_NAME= "FeedReader.db"
        const val DATABASE_VERSION = 1

    }

        }
