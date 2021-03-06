package cn.cuit.microcollege.entity.HttpResultEntity;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/27
 * @Descirption:
 */
public class QuitFromCircleResultBean {

    /**
     * message : 该用户已退出该圈子
     * statusCode : 1
     * isQuited : 1
     */

    private String message;
    private int statusCode;
    private int isQuited;

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

    public int getIsQuited() {
        return isQuited;
    }

    public void setIsQuited(int isQuited) {
        this.isQuited = isQuited;
    }
}
