package com.example.user.xsgl;

import java.io.Serializable;

/**
 * Created by user on 2017/11/14.
 */

public class User implements Serializable{
    String PersonName;
    String Password;

    public User() {
    }

    public User(String personName, String password) {
        PersonName = personName;
        Password = password;
    }
}
