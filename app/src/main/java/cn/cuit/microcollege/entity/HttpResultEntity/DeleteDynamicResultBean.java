package cn.cuit.microcollege.entity.HttpResultEntity;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/25
 * @Descirption:
 */
public class DeleteDynamicResultBean {

    /**
     * statusCode : 0
     * message : 该动态不存在
     */

    private String statusCode;
    private String message;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
