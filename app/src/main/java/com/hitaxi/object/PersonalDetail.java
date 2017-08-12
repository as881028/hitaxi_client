package com.hitaxi.object;

/**
 * Created by DK on 2017/8/2.
 */
//個人資料物件
public class PersonalDetail {
    //個人相關資訊

    public class TradDetail {

    }

    protected String personalCode;
    protected String userArray;
    protected String name;

    protected String callNum;
    protected String setPicture;

    protected String c_Name;
    protected String c_Addr;
    protected String c_Phone;
    protected String c_Fax;


    public void setDetail(String code, String UserArray) {
        this.loginCode = code;
        this.userArray = UserArray;
    }

    String getUserArray() {
        return this.userArray;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTeam(String name, String addr, String phone, String fax) {
        this.c_Name = name;
        this.c_Addr = addr;
        this.c_Phone = phone;
        this.c_Fax = fax;

    }

    public String getTeamName() {
        return this.c_Name;
    }

    public String getTeamAddr() {
        return this.c_Addr;
    }

    public String getTeamPhone() {
        return this.c_Phone;
    }

    public String getTeamFax() {
        return this.c_Fax;
    }

    public void setCallNum(String callNum) {
        this.callNum = callNum;
    }

    public String getCallNum() {
        return this.callNum;
    }


    //登入相關資訊

    //code 代碼
    //-1 => "帳號或密碼錯誤。",
    //-2 => "標章驗證錯誤。",
    //-99 => "未知錯誤。"
    protected String loginCode;

    //errorMsg 錯誤訊息
    protected String errorMsg;

    //token 驗證用token，後面與PHP溝通會用到
    protected String token;

    //使用者ID
    protected String userId;

    //使用者型態
    // 1=>乘客
    //2=>司機
    protected String userType;

    public void setLoginDetail(String code, String errorMsg, String token, String userId, String userType) {
        this.loginCode = code;
        this.errorMsg = errorMsg;
        this.token = token;
        this.userId = userId;
        this.userType = userType;
    }

    public int getUserType() {
        return Integer.parseInt(this.userType);
    }

    public int getLoginCode() {
        return Integer.parseInt(loginCode);
    }

    public String getUserToken() {
        return this.token;
    }

    public String getUserID() {
        return this.userId;
    }
}

