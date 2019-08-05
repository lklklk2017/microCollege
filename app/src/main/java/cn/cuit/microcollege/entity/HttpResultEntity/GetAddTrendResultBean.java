package cn.cuit.microcollege.entity.HttpResultEntity;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/21
 * @Descirption:
 */
public class GetAddTrendResultBean {

    /**
     * message : 动态添加成功！
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
}
