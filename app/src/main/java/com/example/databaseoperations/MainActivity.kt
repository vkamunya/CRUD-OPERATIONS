package com.example.databaseoperations

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import com.example.databaseoperations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myDB= FeedReaderDbHelper(applicationContext)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        binding.create.setOnClickListener {
            val db= myDB.writableDatabase
            val values= ContentValues().apply{
                put(FeedReaderContract.FeedEntry.COLUMN_ADMISSION_NUMBER, "12-9078")
                put(FeedReaderContract.FeedEntry.COLUMN_NAME, "Jacob")
            }
            // Insert the new row returning the primary key value of the new row
            val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null,values)
            Log.d("db","inserted: newRowId")
            db.close()
        }
        binding.update.setOnClickListener {
            val db= myDB.writableDatabase
            // value for one column

            val newName = "James"
            val values= ContentValues().apply{
                put(FeedReaderContract.FeedEntry.COLUMN_NAME, newName)
            }
            //which row to update based on the title
            val selection = "${FeedReaderContract.FeedEntry.COLUMN_ADMISSION_NUMBER} LIKE ?"
            val selectionArgs = arrayOf("12-9078")
            val count =db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs)
            Log.d("db","update: $count")

        }
        binding.delete.setOnClickListener {
            val db= myDB.writableDatabase
            val selection = "${FeedReaderContract.FeedEntry.COLUMN_ADMISSION_NUMBER} LIKE ?"
            val selectionArgs = arrayOf("12-9078")
            val deletedRows= db.delete(
                    FeedReaderContract.FeedEntry.COLUMN_ADMISSION_NUMBER,
                    selection,
                    selectionArgs)
            Log.d("db","delete: $deletedRows")
            db.close()

        }
        binding.read.setOnClickListener {
            val db= myDB.readableDatabase
            val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_ADMISSION_NUMBER, FeedReaderContract.FeedEntry.COLUMN_NAME)
            val selection = "${FeedReaderContract.FeedEntry.COLUMN_ADMISSION_NUMBER} = ?"
            val selectionArgs= arrayOf("12-9078")
            val sortOrder= "${FeedReaderContract.FeedEntry.COLUMN_ADMISSION_NUMBER} DESC"

            val cursor = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            )
            Log.d("db","read: $projection")
            db.close()

        }



    }
}