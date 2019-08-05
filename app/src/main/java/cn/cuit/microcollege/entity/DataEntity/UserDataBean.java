package cn.cuit.microcollege.entity.DataEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/25
 * @Descirption:
 */
public class UserDataBean implements Parcelable {
    /**
     * avatarImgUrl : http://localhost:8080/microCollege/upload/userAvatar/avatar322950.png
     * birthday : 1995-04-28T00:00:00
     * campus : 成都信息工程大学
     * ciphertext :
     * circleAddedCount : 0
     * circleCreatedCount : 0
     * coverImgUrl :
     * email : 592215060@qq.com
     * freeze : 0
     * gender : 男
     * industry : 软件工程师
     * loginState : 1
     * nativePlace : 青海省
     * sign : 6666
     * tel : 15008476607
     * token :
     * userId : 153
     * userRegistTime : 2018-05-15T23:34:06
     * username : Rod Eden
     * userpassword : 123456
     */

    private String avatarImgUrl;
    private Date birthday;
    private String campus;
    private String ciphertext;
    private int circleAddedCount;
    private int circleCreatedCount;
    private String coverImgUrl;
    private String email;
    private int freeze;
    private String gender;
    private String industry;
    private int loginState;
    private String nativePlace;
    private String sign;
    private String tel;
    private String token;
    private int userId;
    private Date userRegistTime;
    private String username;
    private String userpassword;

    public UserDataBean() {
    }

    public UserDataBean(String avatarImgUrl, Date birthday, String campus, String ciphertext, int circleAddedCount, int circleCreatedCount, String coverImgUrl, String email, int freeze, String gender, String industry, int loginState, String nativePlace, String sign, String tel, String token, int userId, Date userRegistTime, String username, String userpassword) {
        this.avatarImgUrl = avatarImgUrl;
        this.birthday = birthday;
        this.campus = campus;
        this.ciphertext = ciphertext;
        this.circleAddedCount = circleAddedCount;
        this.circleCreatedCount = circleCreatedCount;
        this.coverImgUrl = coverImgUrl;
        this.email = email;
        this.freeze = freeze;
        this.gender = gender;
        this.industry = industry;
        this.loginState = loginState;
        this.nativePlace = nativePlace;
        this.sign = sign;
        this.tel = tel;
        this.token = token;
        this.userId = userId;
        this.userRegistTime = userRegistTime;
        this.username = username;
        this.userpassword = userpassword;
    }

    protected UserDataBean(Parcel in) {
        avatarImgUrl = in.readString();
        campus = in.readString();
        ciphertext = in.readString();
        circleAddedCount = in.readInt();
        circleCreatedCount = in.readInt();
        coverImgUrl = in.readString();
        email = in.readString();
        freeze = in.readInt();
        gender = in.readString();
        industry = in.readString();
        loginState = in.readInt();
        nativePlace = in.readString();
        sign = in.readString();
        tel = in.readString();
        token = in.readString();
        userId = in.readInt();
        username = in.readString();
        userpassword = in.readString();
    }

    public static final Creator<UserDataBean> CREATOR = new Creator<UserDataBean>() {
        @Override
        public UserDataBean createFromParcel(Parcel in) {
            return new UserDataBean(in);
        }

        @Override
        public UserDataBean[] newArray(int size) {
            return new UserDataBean[size];
        }
    };

    @Override
    public String toString() {
        return "UserDataBean{" +
                "avatarImgUrl='" + avatarImgUrl + '\'' +
                ", birthday=" + birthday +
                ", campus='" + campus + '\'' +
                ", ciphertext='" + ciphertext + '\'' +
                ", circleAddedCount=" + circleAddedCount +
                ", circleCreatedCount=" + circleCreatedCount +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", email='" + email + '\'' +
                ", freeze=" + freeze +
                ", gender='" + gender + '\'' +
                ", industry='" + industry + '\'' +
                ", loginState=" + loginState +
                ", nativePlace='" + nativePlace + '\'' +
                ", sign='" + sign + '\'' +
                ", tel='" + tel + '\'' +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                ", userRegistTime=" + userRegistTime +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                '}';
    }

    public String getAvatarImgUrl() {
        return avatarImgUrl;
    }

    public void setAvatarImgUrl(String avatarImgUrl) {
        this.avatarImgUrl = avatarImgUrl;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public int getCircleAddedCount() {
        return circleAddedCount;
    }

    public void setCircleAddedCount(int circleAddedCount) {
        this.circleAddedCount = circleAddedCount;
    }

    public int getCircleCreatedCount() {
        return circleCreatedCount;
    }

    public void setCircleCreatedCount(int circleCreatedCount) {
        this.circleCreatedCount = circleCreatedCount;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getUserRegistTime() {
        return userRegistTime;
    }

    public void setUserRegistTime(Date userRegistTime) {
        this.userRegistTime = userRegistTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatarImgUrl);
        dest.writeString(campus);
        dest.writeString(ciphertext);
        dest.writeInt(circleAddedCount);
        dest.writeInt(circleCreatedCount);
        dest.writeString(coverImgUrl);
        dest.writeString(email);
        dest.writeInt(freeze);
        dest.writeString(gender);
        dest.writeString(industry);
        dest.writeInt(loginState);
        dest.writeString(nativePlace);
        dest.writeString(sign);
        dest.writeString(tel);
        dest.writeString(token);
        dest.writeInt(userId);
        dest.writeString(username);
        dest.writeString(userpassword);
    }
}
