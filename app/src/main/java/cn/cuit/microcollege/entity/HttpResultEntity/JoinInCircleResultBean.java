package cn.cuit.microcollege.entity.HttpResultEntity;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/27
 * @Descirption:
 */
public class JoinInCircleResultBean {

    /**
     * message : 该用户已加入
     * statusCode : 1
     * isJoined : 1
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
