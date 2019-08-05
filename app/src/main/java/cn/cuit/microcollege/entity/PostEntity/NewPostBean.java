package cn.cuit.microcollege.entity.PostEntity;

import cn.cuit.microcollege.utils.Config;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class NewPostBean {
    private String key = Config.APPKEY_MOB;
    private String cid = "";
    private int page = 1;
    private int size = 10;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "NewPostBean{" +
                "key='" + key + '\'' +
                ", cid='" + cid + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public NewPostBean(String key, String cid, int page, int size) {
        this.key = key;
        this.cid = cid;
        this.page = page;
        this.size = size;
    }

    public NewPostBean() {
    }
}
