package cn.cuit.microcollege.entity.HttpResultEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/25
 * @Descirption:
 */
public class GetUserBaseInfoResultBean {

    /**
     * message : success
     * statusCode : 1
     * userList : [{"avatarImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/location_red89c14.png","birthday":"1995-04-28T00:00:00","campus":"成都信息工程大学","ciphertext":"","circleAddedCount":0,"circleCreatedCount":0,"coverImgUrl":"","email":"592215060@qq.com","freeze":0,"gender":"男","industry":"软件工程师","loginState":1,"nativePlace":"青海省","sign":"6666","tel":"15008476607","token":"","userId":153,"userRegistTime":"2018-05-15T23:34:06","username":"Rod Eden","userpassword":"123456"}]
     */

    private String message;
    private String statusCode;
    private List<UserListBean> userList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public static class UserListBean implements Parcelable {
        /**
         * avatarImgUrl : http://localhost:8080/microCollege/upload/userAvatar/location_red89c14.png
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
        private String birthday;
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
        private String userRegistTime;
        private String username;
        private String userpassword;

        protected UserListBean(Parcel in) {
            avatarImgUrl = in.readString();
            birthday = in.readString();
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
            userRegistTime = in.readString();
            username = in.readString();
            userpassword = in.readString();
        }

        public static final Creator<UserListBean> CREATOR = new Creator<UserListBean>() {
            @Override
            public UserListBean createFromParcel(Parcel in) {
                return new UserListBean(in);
            }

            @Override
            public UserListBean[] newArray(int size) {
                return new UserListBean[size];
            }
        };

        public String getAvatarImgUrl() {
            return avatarImgUrl;
        }

        public void setAvatarImgUrl(String avatarImgUrl) {
            this.avatarImgUrl = avatarImgUrl;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
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

        public String getUserRegistTime() {
            return userRegistTime;
        }

        public void setUserRegistTime(String userRegistTime) {
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
            dest.writeString(birthday);
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
            dest.writeString(userRegistTime);
            dest.writeString(username);
            dest.writeString(userpassword);
        }
    }
}
