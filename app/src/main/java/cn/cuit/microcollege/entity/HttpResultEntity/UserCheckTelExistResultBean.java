package cn.cuit.microcollege.entity.HttpResultEntity;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/7
 * @Descirption:
 */
public class UserCheckTelExistResultBean {

    /**
     * isExist : 0
     * message : 电话号码不存在
     * statusCode : 1
     */

    private int isExist;
    private String message;
    private String statusCode;

    public int getIsExist() {
        return isExist;
    }

    public void setIsExist(int isExist) {
        this.isExist = isExist;
    }

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
        return "UserCheckTelExistResultBean{" +
                "isExist=" + isExist +
                ", message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
