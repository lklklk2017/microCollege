package cn.cuit.microcollege.entity.HttpResultEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/10
 * @Descirption:
 */
public class GetCommentResultBean  {

    /**
     * message : success
     * statusCode : 1
     * comments : [{"commentContent":"这是对53动态的评论内容","commentId":64,"commentTime":"2019-05-04T09:37:00","commentType":0,"toId":0,"trend":{"dynamicId":53},"user":{"avatarImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/cut86f28.jpg","userId":305,"username":"dsfds"}}]
     */

    private String message;
    private String statusCode;
    private List<CommentsBean> comments;

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

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean implements Parcelable {
        /**
         * commentContent : 这是对53动态的评论内容
         * commentId : 64
         * commentTime : 2019-05-04T09:37:00
         * commentType : 0
         * toId : 0
         * trend : {"dynamicId":53}
         * user : {"avatarImgUrl":"http://localhost:8080/microCollege/upload/userAvatar/cut86f28.jpg","userId":305,"username":"dsfds"}
         */

        private String commentContent;
        private int commentId;
        private String commentTime;
        private int commentType;
        private int toId;
        private TrendBean trend;
        private UserBean user;

        public CommentsBean() {
        }

        protected CommentsBean(Parcel in) {
            commentContent = in.readString();
            commentId = in.readInt();
            commentTime = in.readString();
            commentType = in.readInt();
            toId = in.readInt();
            trend = in.readParcelable(TrendBean.class.getClassLoader());
            user = in.readParcelable(UserBean.class.getClassLoader());
        }

        public static final Creator<CommentsBean> CREATOR = new Creator<CommentsBean>() {
            @Override
            public CommentsBean createFromParcel(Parcel in) {
                return new CommentsBean(in);
            }

            @Override
            public CommentsBean[] newArray(int size) {
                return new CommentsBean[size];
            }
        };

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public String getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(String commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentType() {
            return commentType;
        }

        public void setCommentType(int commentType) {
            this.commentType = commentType;
        }

        public int getToId() {
            return toId;
        }

        public void setToId(int toId) {
            this.toId = toId;
        }

        public TrendBean getTrend() {
            return trend;
        }

        public void setTrend(TrendBean trend) {
            this.trend = trend;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(commentContent);
            dest.writeInt(commentId);
            dest.writeString(commentTime);
            dest.writeInt(commentType);
            dest.writeInt(toId);
            dest.writeParcelable(trend, flags);
            dest.writeParcelable(user, flags);
        }

        public static class TrendBean implements Parcelable{
            /**
             * dynamicId : 53
             */

            private int dynamicId;

            protected TrendBean(Parcel in) {
                dynamicId = in.readInt();
            }

            public static final Creator<TrendBean> CREATOR = new Creator<TrendBean>() {
                @Override
                public TrendBean createFromParcel(Parcel in) {
                    return new TrendBean(in);
                }

                @Override
                public TrendBean[] newArray(int size) {
                    return new TrendBean[size];
                }
            };

            public int getDynamicId() {
                return dynamicId;
            }

            public void setDynamicId(int dynamicId) {
                this.dynamicId = dynamicId;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(dynamicId);
            }
        }

        public static class UserBean implements Parcelable {
            /**
             * avatarImgUrl : http://localhost:8080/microCollege/upload/userAvatar/cut86f28.jpg
             * userId : 305
             * username : dsfds
             */

            private String avatarImgUrl;
            private int userId;
            private String username;

            protected UserBean(Parcel in) {
                avatarImgUrl = in.readString();
                userId = in.readInt();
                username = in.readString();
            }

            public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                @Override
                public UserBean createFromParcel(Parcel in) {
                    return new UserBean(in);
                }

                @Override
                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };

            public String getAvatarImgUrl() {
                return avatarImgUrl;
            }

            public void setAvatarImgUrl(String avatarImgUrl) {
                this.avatarImgUrl = avatarImgUrl;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(avatarImgUrl);
                dest.writeInt(userId);
                dest.writeString(username);
            }
        }
    }
}
