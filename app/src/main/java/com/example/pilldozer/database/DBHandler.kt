package com.example.pilldozer.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pilldozer.data.Medicine

class DBHandler internal constructor(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_MEDICINE_TABLE = ("CREATE TABLE " +
                TABLE_MEDICINES + "(" + COLUMN_MEDICINE_ID + " INTEGER PRIMARY KEY," + COLUMN_MEDICINE_NAME
                + " TEXT, " + COLUMN_MEDICINE_QUANTITY + " TEXT, " + COLUMN_MEDICINE_DESCRIPTION + " TEXT " + ")")
        db.execSQL(CREATE_MEDICINE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICINES")
        onCreate(db)
    }

    fun loadHandler(): String {
        var result = ""
        val query = "Select*FROM $TABLE_MEDICINES"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val result_0 = cursor.getInt(0)
            val result_1 = cursor.getString(1)
            result += result_0.toString() + " " + result_1 +
                    System.getProperty("line.separator")
        }
        cursor.close()
        db.close()
        if (result == "") result = "No Record Found"
        return result
    }
    fun addHandler(medicine: Medicine): Long {
        val id: Long
        val values = ContentValues()
        values.put(COLUMN_MEDICINE_ID, medicine.id)
        values.put(COLUMN_MEDICINE_NAME, medicine.name)
        values.put(COLUMN_MEDICINE_QUANTITY, medicine.quantity)
        values.put(COLUMN_MEDICINE_DESCRIPTION, medicine.description)
        val db = this.writableDatabase
        id = db.insert(TABLE_MEDICINES, null, values)
        db.close()
        return id
    }
    /*
    fun updateHandler(medicine: Medicine): Boolean {
        val db = this.writableDatabase
        val args = ContentValues()
        args.put(COLUMN_MEDICINE_ID, medicine.id)
        args.put(COLUMN_MEDICINE_NAME, medicine.name)
        return db.update(TABLE_MEDICINES, args, "$COLUMN_MEDICINE_ID=${medicine.id}", null) > 0
    }
    */

    fun deleteHandler(ID: Int): Boolean {
        var result = false
        val query = "Select*FROM $TABLE_MEDICINES WHERE $COLUMN_MEDICINE_ID = '$ID'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id= cursor.getString(0).toInt()
            db.delete(TABLE_MEDICINES, "$COLUMN_MEDICINE_ID=?", arrayOf(
                java.lang.String.valueOf(id)
            ))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "pilldozerDB.db"
        private const val TABLE_MEDICINES = "medicines"
        private const val COLUMN_MEDICINE_ID = "Id"
        private const val COLUMN_MEDICINE_NAME = "Name"
        private const val COLUMN_MEDICINE_QUANTITY = "Quantity"
        private const val COLUMN_MEDICINE_DESCRIPTION = "Description"
    }
}