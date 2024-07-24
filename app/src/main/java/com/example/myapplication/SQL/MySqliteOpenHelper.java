package com.example.myapplication.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *数据库管理类,负责管理数据库的创建、升级工作
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {
    //数据库名字
    public static final String DB_NAME = "boot.db";

    //数据库版本
    public static final int DB_VERSION = 1;
    private Context context;

    public MySqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    /**
     * 在数据库首次创建的时候调用，创建表以及可以进行一些表数据的初始化
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        //_id为主键并且自增长一般命名为_id
        String userSql = "create table user(id integer primary key autoincrement,account,name,password)";
        db.execSQL(userSql);
    }
    /**
     * 数据库升级的时候回调该方法，在数据库版本号DB_VERSION升级的时候才会调用
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //给表添加一个字段
        //db.execSQL("alter table person add age integer");
    }

    /**
     * 数据库打开的时候回调该方法
     *
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}

