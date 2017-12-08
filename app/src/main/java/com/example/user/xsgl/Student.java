package com.example.user.xsgl;

import java.io.Serializable;

/**
 * Created by user on 2017/11/15.
 */

public class Student implements Serializable {
    int _id;
    String Sno;
    String Name;
    String Gender;
    String Like;

    public Student() {
    }

    public Student(int _id, String sno, String name, String gender, String like) {
        this._id = _id;
        Sno = sno;
        Name = name;
        Gender = gender;
        Like = like;
    }
}
