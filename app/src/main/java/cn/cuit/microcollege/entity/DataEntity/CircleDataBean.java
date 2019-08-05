package cn.cuit.microcollege.entity.DataEntity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/11
 * @Descirption:
 */
public class CircleDataBean implements Parcelable {

    /**
     * circleAvaterUrl : null
     * circleCreatedTime : 2019-02-04T10:50:11
     * circleId : 7
     * circleName : 生活小姿势
     * circleTitle : 一姿一势 往往生活带给我们不一样的东西
     * circleTopicImgUrl : null
     * dynamicJoinedCount : 0
     * founderId : 9
     * userJoinedCount : 0
     */

    private String circleAvaterUrl;
    private String circleCreatedTime;
    private int circleId;
    private String circleName;
    private String circleTitle;
    private String circleTopicImgUrl;
    private int dynamicJoinedCount;
    private int founderId;
    private int userJoinedCount;

    protected CircleDataBean(Parcel in) {
        circleAvaterUrl = in.readString();
        circleCreatedTime = in.readString();
        circleId = in.readInt();
        circleName = in.readString();
        circleTitle = in.readString();
        circleTopicImgUrl = in.readString();
        dynamicJoinedCount = in.readInt();
        founderId = in.readInt();
        userJoinedCount = in.readInt();
    }

    public static final Creator<CircleDataBean> CREATOR = new Creator<CircleDataBean>() {
        @Override
        public CircleDataBean createFromParcel(Parcel in) {
            return new CircleDataBean(in);
        }

        @Override
        public CircleDataBean[] newArray(int size) {
            return new CircleDataBean[size];
        }
    };

    @Override
    public String toString() {
        return "CircleDataBean{" +
                "circleAvaterUrl='" + circleAvaterUrl + '\'' +
                ", circleCreatedTime='" + circleCreatedTime + '\'' +
                ", circleId=" + circleId +
                ", circleName='" + circleName + '\'' +
                ", circleTitle='" + circleTitle + '\'' +
                ", circleTopicImgUrl='" + circleTopicImgUrl + '\'' +
                ", dynamicJoinedCount=" + dynamicJoinedCount +
                ", founderId=" + founderId +
                ", userJoinedCount=" + userJoinedCount +
                '}';
    }

    public CircleDataBean() {
    }

    public String getCircleAvaterUrl() {
        return circleAvaterUrl;
    }

    public void setCircleAvaterUrl(String circleAvaterUrl) {
        this.circleAvaterUrl = circleAvaterUrl;
    }

    public String getCircleCreatedTime() {
        return circleCreatedTime;
    }

    public void setCircleCreatedTime(String circleCreatedTime) {
        this.circleCreatedTime = circleCreatedTime;
    }

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCircleTitle() {
        return circleTitle;
    }

    public void setCircleTitle(String circleTitle) {
        this.circleTitle = circleTitle;
    }

    public String getCircleTopicImgUrl() {
        return circleTopicImgUrl;
    }

    public void setCircleTopicImgUrl(String circleTopicImgUrl) {
        this.circleTopicImgUrl = circleTopicImgUrl;
    }

    public int getDynamicJoinedCount() {
        return dynamicJoinedCount;
    }

    public void setDynamicJoinedCount(int dynamicJoinedCount) {
        this.dynamicJoinedCount = dynamicJoinedCount;
    }

    public int getFounderId() {
        return founderId;
    }

    public void setFounderId(int founderId) {
        this.founderId = founderId;
    }

    public int getUserJoinedCount() {
        return userJoinedCount;
    }

    public void setUserJoinedCount(int userJoinedCount) {
        this.userJoinedCount = userJoinedCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(circleAvaterUrl);
        dest.writeString(circleCreatedTime);
        dest.writeInt(circleId);
        dest.writeString(circleName);
        dest.writeString(circleTitle);
        dest.writeString(circleTopicImgUrl);
        dest.writeInt(dynamicJoinedCount);
        dest.writeInt(founderId);
        dest.writeInt(userJoinedCount);
    }
}
