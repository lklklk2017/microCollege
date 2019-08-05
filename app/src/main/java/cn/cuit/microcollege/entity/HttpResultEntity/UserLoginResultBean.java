package cn.cuit.microcollege.entity.HttpResultEntity;

import java.util.Date;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/6
 * @Descirption:
 */
public class UserLoginResultBean {


    /**
     * message : scuccess
     * statusCode : 1
     * user : {"avatarImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/avatar322950.png","birthday":"1995-04-28T00:00:00","campus":"成都信息工程大学","ciphertext":"","circleAddedCount":0,"circleCreatedCount":0,"coverImgUrl":"","email":"592215060@qq.com","freeze":0,"gender":"男","industry":"软件工程师","loginState":1,"nativePlace":"青海省","sign":"6666","tel":"15008476607","token":"","userId":153,"userRegistTime":"2018-05-15T23:34:06","username":"Rod Eden","userpassword":"123456"}
     */

    private String message;
    private int statusCode;
    private UserBean user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
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

        @Override
        public String toString() {
            return "UserBean{" +
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

    }
}
