package cn.cuit.microcollege.entity.HttpResultEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/8
 * @Descirption:
 */
public class GetMyCircleResultBean {

    /**
     * message : success
     * statusCode : 1
     * circles : [{"circleAvaterUrl":null,"circleCreatedTime":"2019-02-04T10:50:11","circleId":7,"circleName":"生活小姿势","circleTitle":"一姿一势 往往生活带给我们不一样的东西","circleTopicImgUrl":null,"dynamicJoinedCount":0,"founderId":9,"userJoinedCount":0},{"circleAvaterUrl":"http://localhost:8080/microCollege/upload/userAvatar/avatar36a789.png","circleCreatedTime":"2019-02-03T10:50:11","circleId":4,"circleName":"即刻夜谈会","circleTitle":"即刻首个个人中心交谈会","circleTopicImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/avatar3c18ef.png","dynamicJoinedCount":0,"founderId":9,"userJoinedCount":0},{"circleAvaterUrl":"http://localhost:8080/microCollege/upload/userAvatar/avatar3013fd.png","circleCreatedTime":"2019-02-01T10:50:11","circleId":2,"circleName":"工程师日常","circleTitle":"这是工程师的世界","circleTopicImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/tel7889e.png","dynamicJoinedCount":12,"founderId":153,"userJoinedCount":10}]
     */

    private String message;
    private String statusCode;
    private List<CirclesBean> circles;

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

    public List<CirclesBean> getCircles() {
        return circles;
    }

    public void setCircles(List<CirclesBean> circles) {
        this.circles = circles;
    }

    public static class CirclesBean implements Parcelable {
        public CirclesBean() {
        }

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

        @Override
        public String toString() {
            return "CirclesBean{" +
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

        protected CirclesBean(Parcel in) {
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

        public static final Creator<CirclesBean> CREATOR = new Creator<CirclesBean>() {
            @Override
            public CirclesBean createFromParcel(Parcel in) {
                return new CirclesBean(in);
            }

            @Override
            public CirclesBean[] newArray(int size) {
                return new CirclesBean[size];
            }
        };

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

    @Override
    public String toString() {
        return "GetMyCircleResultBean{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", circles=" + circles +
                '}';
    }
}
