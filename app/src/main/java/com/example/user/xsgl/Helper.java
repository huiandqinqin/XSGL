package com.example.user.xsgl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by user on 2017/11/14.
 */

public class Helper extends SQLiteOpenHelper{

    SQLiteDatabase db;

    public Helper(Context context) {
        super(context, "XSGL.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists tblUser");
        db.execSQL("create table if not exists tblUser(PersonName text primary key,Password text)");
        db.execSQL("delete from tblUser");
        db.execSQL("insert into tblUser values('admin','123')");

        db.execSQL("drop table if exists tblStudent");
        db.execSQL("create table if not exists tblStudent(_id integer primary key autoincrement,Sno text,Name text,Gender text,Like text)");
        db.execSQL("delete from tblStudent");
        db.execSQL("insert into tblStudent(Sno,Name,Gender,Like) values('1','admin1','男','下棋')");
        db.execSQL("insert into tblStudent(Sno,Name,Gender,Like) values('2','admin2','女','听歌')");
    }

    public Cursor queryUser() {
        db=this.getReadableDatabase();
        Cursor cursor=db.query("tblUser",null,null,null,null,null,null);
        return cursor;
    }

    public Cursor queryUserByPersonName(String PersonName) {
        db=this.getReadableDatabase();
        Cursor cursor=db.query("tblUser",null,"PersonName='"+PersonName+"'",null,null,null,null);
        return cursor;
    }

    public Cursor queryStudent() {
        db=this.getReadableDatabase();
        Cursor cursor=db.query("tblStudent",null,null,null,null,null,null);
        return cursor;
    }

   public long insertStudent(Student s){
       db=this.getWritableDatabase();
       ContentValues cv=new ContentValues();
       cv.put("Sno",s.Sno);
       cv.put("Name",s.Name);
       cv.put("Gender",s.Gender);
       cv.put("Like",s.Like);
       long id=(long)db.insert("tblStudent",null,cv);
       return id;
   }

    public void deleteStudentBySno(String Sno){
        db=this.getWritableDatabase();
        db.delete("tblStudent","Sno='"+Sno+"'",null);
    }

    public void deleteStudentByName(String Name){
        db=this.getWritableDatabase();
        db.delete("tblStudent","Name='"+Name+"'",null);
    }

    public void updateStudent(Student s){
        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Name",s.Name);
        cv.put("Gender",s.Gender);
        cv.put("Like",s.Like);
        db.update("tblStudent",cv,"Sno='"+s.Sno+"'",null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
