package com.example.knight.libraryutils.bean;

import android.os.Message;

import java.io.Serializable;

/**
 * Base Message used for Handler.
 * Created by knight on 2017/3/20.
 */

public class OkMessage implements Serializable{

    public int what;

    public Message build(){
        Message msg = Message.obtain();
        msg.what = this.what;
        msg.obj = this;
        return msg;
    }
}
