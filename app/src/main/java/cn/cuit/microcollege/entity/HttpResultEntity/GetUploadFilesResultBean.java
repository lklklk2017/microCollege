package cn.cuit.microcollege.entity.HttpResultEntity;

import java.util.List;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/21
 * @Descirption:
 */
public class GetUploadFilesResultBean {

    /**
     * message : 上传成功
     * statusCode : 1
     * imgUrlArr : ["http://localhost:8080/microCollege/upload/userAvatar/IMG_20190422_0659292af1e.jpg","http://localhost:8080/microCollege/upload/userAvatar/IMG_20190504_074303ef5ac.jpg","http://localhost:8080/microCollege/upload/userAvatar/IMG_20190401_12371533b03.jpg"]
     */

    private String message;
    private String statusCode;
    private List<String> imgUrlArr;

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

    public List<String> getImgUrlArr() {
        return imgUrlArr;
    }

    public void setImgUrlArr(List<String> imgUrlArr) {
        this.imgUrlArr = imgUrlArr;
    }
}
