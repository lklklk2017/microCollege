package cn.cuit.microcollege.entity.DataEntity;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/1
 * @Descirption: 从系统相册返回并加载至控件的databean
 */
public class ImageDataBean {
    private Integer type;
    private Object imgUrl; //1.string：文件路径  2.int 系统资源

    public ImageDataBean() {

    }

    public ImageDataBean(int type, Object imgUrl) {
        this.type = type;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ImageDataBean{" +
                "type=" + type +
                ", imgUrl='" + String.valueOf(imgUrl) + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
