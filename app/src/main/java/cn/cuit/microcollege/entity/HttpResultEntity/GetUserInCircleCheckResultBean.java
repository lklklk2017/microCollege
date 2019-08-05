package cn.cuit.microcollege.entity.HttpResultEntity;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/24
 * @Descirption:
 */
public class GetUserInCircleCheckResultBean {

    /**
     * message : 该用户不存在
     * statusCode : 0
     * isJoined : 0
     */

    private String message;
    private int statusCode;
    private int isJoined;

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

    public int getIsJoined() {
        return isJoined;
    }

    public void setIsJoined(int isJoined) {
        this.isJoined = isJoined;
    }
}
