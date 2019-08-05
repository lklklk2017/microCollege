package cn.cuit.microcollege.entity.HttpResultEntity;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/7
 * @Descirption:
 */
public class UserRegistResultBean {

    /**
     * message : 用户添加成功
     * statusCode : 1
     */

    private String message;
    private String statusCode;

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

    @Override
    public String toString() {
        return "UserRegistResultBean{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
