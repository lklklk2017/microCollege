package cn.cuit.microcollege.entity.HttpResultEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/10
 * @Descirption:
 */
public class GetDynamicWithCircleResultBean {

    @Override
    public String toString() {
        return "GetDynamicWithCircleResultBean{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", trends=" + trends +
                '}';
    }

    /**
     * message : success
     * statusCode : 1
     * trends : [{"circle":{"circleAvaterUrl":"http://localhost:8080/microCollege/upload/userAvatar/wel_02ad7c6.jpg","circleCreatedTime":"2019-02-03T10:50:11","circleId":4,"circleName":"即刻夜谈会","circleTitle":"即刻首个个人中心交谈会","circleTopicImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/circle_topic_bluedcafe.png","dynamicJoinedCount":0,"founderId":9,"userJoinedCount":0},"commentCount":50,"dynamicContent":"测试动态1","dynamicId":52,"dynamicType":1,"dynamicUserAvaterUrl":"  ","dynamicUserName":"测试添加","imageList":"http://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,\r\nhttp://localhost:8080/microCollege/upload/userAvatar/avatar324677.png","likeCount":55,"location":" ","publishTime":"2019-04-25T12:52:00"},{"circle":{"circleAvaterUrl":"http://localhost:8080/microCollege/upload/userAvatar/feedback2cab6.png","circleCreatedTime":"2019-02-01T10:50:11","circleId":2,"circleName":"工程师日常","circleTitle":"这是工程师的世界","circleTopicImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/wel_01_finaldae33.jpg","dynamicJoinedCount":12,"founderId":153,"userJoinedCount":10},"commentCount":50,"dynamicContent":"测试动态1","dynamicId":44,"dynamicType":1,"dynamicUserAvaterUrl":" ","dynamicUserName":"测试添加","imageList":"http://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,\r\nhttp://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,\r\nhttp://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,\r\nhttp://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,\r\nhttp://localhost:8080/microCollege/upload/userAvatar/avatar324677.png","likeCount":99,"location":" ","publishTime":"2019-03-25T12:52:00"},{"circle":{"circleAvaterUrl":"http://localhost:8080/microCollege/upload/userAvatar/wel_02ad7c6.jpg","circleCreatedTime":"2019-02-03T10:50:11","circleId":4,"circleName":"即刻夜谈会","circleTitle":"即刻首个个人中心交谈会","circleTopicImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/circle_topic_bluedcafe.png","dynamicJoinedCount":0,"founderId":9,"userJoinedCount":0},"commentCount":50,"dynamicContent":"测试动态1","dynamicId":51,"dynamicType":1,"dynamicUserAvaterUrl":" ","dynamicUserName":"测试添加","imageList":"http://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,\r\nhttp://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,http://localhost:8080/microCollege/upload/userAvatar/avatar324677.png","likeCount":66,"location":" ","publishTime":"2019-03-25T12:52:00"},{"circle":{"circleAvaterUrl":"http://localhost:8080/microCollege/upload/userAvatar/wel_02ad7c6.jpg","circleCreatedTime":"2019-02-03T10:50:11","circleId":4,"circleName":"即刻夜谈会","circleTitle":"即刻首个个人中心交谈会","circleTopicImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/circle_topic_bluedcafe.png","dynamicJoinedCount":0,"founderId":9,"userJoinedCount":0},"commentCount":50,"dynamicContent":"测试动态1","dynamicId":53,"dynamicType":0,"dynamicUserAvaterUrl":" ","dynamicUserName":"测试添加","imageList":"http://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,\r\nhttp://localhost:8080/microCollege/upload/userAvatar/avatar324677.png","likeCount":44,"location":" ","publishTime":"2019-03-25T12:52:00"},{"circle":{"circleAvaterUrl":"http://localhost:8080/microCollege/upload/userAvatar/cut787dd.jpg","circleCreatedTime":"2019-02-02T10:50:11","circleId":3,"circleName":"情感大师","circleTitle":"这里找到TA 你我之心 ","circleTopicImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/banner73d6b.png","dynamicJoinedCount":0,"founderId":157,"userJoinedCount":0},"commentCount":50,"dynamicContent":"","dynamicId":15,"dynamicType":1,"dynamicUserAvaterUrl":" ","dynamicUserName":"Rod Eden","imageList":"http://localhost:8080/microCollege/upload/userAvatar/avatar324677.png","likeCount":100,"location":" ","publishTime":"2018-03-25T12:52:00"}]
     */



    private String message;
    private String statusCode;
    private List<TrendsBean> trends;

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

    public List<GetDynamicWithCircleResultBean.TrendsBean> getTrends() {
        return trends;
    }

    public void setTrends(List<TrendsBean> trends) {
        this.trends = trends;
    }

    public static class TrendsBean implements Parcelable {
        public TrendsBean() {
        }

        protected TrendsBean(Parcel in) {
            circle = in.readParcelable(CircleBean.class.getClassLoader());
            commentCount = in.readInt();
            dynamicContent = in.readString();
            dynamicId = in.readInt();
            dynamicType = in.readInt();
            dynamicUserAvaterUrl = in.readString();
            dynamicUserName = in.readString();
            imageList = in.readString();
            likeCount = in.readInt();
            location = in.readString();
            publishTime = in.readString();
        }

        public static final Creator<TrendsBean> CREATOR = new Creator<TrendsBean>() {
            @Override
            public TrendsBean createFromParcel(Parcel in) {
                return new TrendsBean(in);
            }

            @Override
            public TrendsBean[] newArray(int size) {
                return new TrendsBean[size];
            }
        };

        @Override
        public String toString() {
            return "TrendsBean{" +
                    "circle=" + circle +
                    ", commentCount=" + commentCount +
                    ", dynamicContent='" + dynamicContent + '\'' +
                    ", dynamicId=" + dynamicId +
                    ", dynamicType=" + dynamicType +
                    ", dynamicUserAvaterUrl='" + dynamicUserAvaterUrl + '\'' +
                    ", dynamicUserName='" + dynamicUserName + '\'' +
                    ", imageList='" + imageList + '\'' +
                    ", likeCount=" + likeCount +
                    ", location='" + location + '\'' +
                    ", publishTime='" + publishTime + '\'' +
                    '}';
        }

        /**
         * circle : {"circleAvaterUrl":"http://localhost:8080/microCollege/upload/userAvatar/wel_02ad7c6.jpg","circleCreatedTime":"2019-02-03T10:50:11","circleId":4,"circleName":"即刻夜谈会","circleTitle":"即刻首个个人中心交谈会","circleTopicImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/circle_topic_bluedcafe.png","dynamicJoinedCount":0,"founderId":9,"userJoinedCount":0}
         * commentCount : 50
         * dynamicContent : 测试动态1
         * dynamicId : 52
         * dynamicType : 1
         * dynamicUserAvaterUrl :
         * dynamicUserName : 测试添加
         * imageList : http://localhost:8080/microCollege/upload/userAvatar/avatar324677.png,
         http://localhost:8080/microCollege/upload/userAvatar/avatar324677.png
         * likeCount : 55
         * location :
         * publishTime : 2019-04-25T12:52:00
         */

        private CircleBean circle;
        private int commentCount;
        private String dynamicContent;
        private int dynamicId;
        private int dynamicType;
        private String dynamicUserAvaterUrl;
        private String dynamicUserName;
        private String imageList;
        private int likeCount;
        private String location;
        private String publishTime;

        public CircleBean getCircle() {
            return circle;
        }

        public void setCircle(CircleBean circle) {
            this.circle = circle;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getDynamicContent() {
            return dynamicContent;
        }

        public void setDynamicContent(String dynamicContent) {
            this.dynamicContent = dynamicContent;
        }

        public int getDynamicId() {
            return dynamicId;
        }

        public void setDynamicId(int dynamicId) {
            this.dynamicId = dynamicId;
        }

        public int getDynamicType() {
            return dynamicType;
        }

        public void setDynamicType(int dynamicType) {
            this.dynamicType = dynamicType;
        }

        public String getDynamicUserAvaterUrl() {
            return dynamicUserAvaterUrl;
        }

        public void setDynamicUserAvaterUrl(String dynamicUserAvaterUrl) {
            this.dynamicUserAvaterUrl = dynamicUserAvaterUrl;
        }

        public String getDynamicUserName() {
            return dynamicUserName;
        }

        public void setDynamicUserName(String dynamicUserName) {
            this.dynamicUserName = dynamicUserName;
        }

        public String getImageList() {
            return imageList;
        }

        public void setImageList(String imageList) {
            this.imageList = imageList;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(circle, flags);
            dest.writeInt(commentCount);
            dest.writeString(dynamicContent);
            dest.writeInt(dynamicId);
            dest.writeInt(dynamicType);
            dest.writeString(dynamicUserAvaterUrl);
            dest.writeString(dynamicUserName);
            dest.writeString(imageList);
            dest.writeInt(likeCount);
            dest.writeString(location);
            dest.writeString(publishTime);
        }

        public static class CircleBean implements Parcelable {
            protected CircleBean(Parcel in) {
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

            public static final Creator<CircleBean> CREATOR = new Creator<CircleBean>() {
                @Override
                public CircleBean createFromParcel(Parcel in) {
                    return new CircleBean(in);
                }

                @Override
                public CircleBean[] newArray(int size) {
                    return new CircleBean[size];
                }
            };

            @Override
            public String toString() {
                return "CircleBean{" +
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

            /**
             * circleAvaterUrl : http://localhost:8080/microCollege/upload/userAvatar/wel_02ad7c6.jpg
             * circleCreatedTime : 2019-02-03T10:50:11
             * circleId : 4
             * circleName : 即刻夜谈会
             * circleTitle : 即刻首个个人中心交谈会
             * circleTopicImgUrl : http://localhost:8080/microCollege/upload/userAvatar/circle_topic_bluedcafe.png
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
    }
}
