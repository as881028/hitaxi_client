package com.hitaxi.tools;

/**
 * Created by DK on 2017/8/5.
 */

public class vaildTool {
    //驗證密碼格式
    public static boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4 && password.length() < 16;
    }
}
