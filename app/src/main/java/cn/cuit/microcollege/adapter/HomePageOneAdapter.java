package cn.cuit.microcollege.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseRcyAdapter;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/23
 * @Descirption: home-fragment-pageOne(所有)
 */
public class HomePageOneAdapter extends BaseRcyAdapter<GetDynamicResultBean.TrendsBean, RecyclerView.ViewHolder> {

    public HomePageOneAdapter() {
        super();
    }

    public HomePageOneAdapter(List<GetDynamicResultBean.TrendsBean> list) {
        super(list);
    }

    @Override
    public int getItemViewType(int position) {
        return getmList().get(position).getDynamicType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_zero, parent, false);
            return new Zero(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_one, parent, false);
            return new One(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_two, parent, false);
            return new Two(inflate);
        } else if (viewType == 3) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_three, parent, false);
            return new Three(inflate);
        } else if (viewType == 4) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_four, parent, false);
            return new Four(inflate);
        } else if (viewType == 5) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_five, parent, false);
            return new Five(inflate);
        } else if (viewType == 6) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_six, parent, false);
            return new Six(inflate);
        } else if (viewType == 7) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_seven, parent, false);
            return new Seven(inflate);
        } else if (viewType == 8) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_eight, parent, false);
            return new Eight(inflate);
        } else {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pageone_rcy_item_type_nine, parent, false);
            return new Nine(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int itemViewType = getItemViewType(position);
        Context myContext = MyApplication.getMyContext();
        final GetDynamicResultBean.TrendsBean trendsBean = getmList().get(position);

        /**
         * 0
         */
        if (itemViewType == 0) {//零布局
            final Zero mHolder = (Zero) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.INVISIBLE);
            }

            //content img

            //circle tag
            final GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }

            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 1
         */
        if (itemViewType == 1) {
            final One mHolder = (One) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                //page one
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(imageList))
                        .apply(new RequestOptions()
                                .centerCrop()
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mContentImgOneImg);

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 2
         */
        if (itemViewType == 2) {
            final Two mHolder = (Two) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions()
                                    .centerCrop()
                                    .placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOneImg);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwoImg);
                }

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
                mHolder.mContentImgTwoImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 3
         */
        if (itemViewType == 3) {
            final Three mHolder = (Three) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOneImg);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwoImg);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThreeImg);
                }

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
                mHolder.mContentImgTwoImg.setVisibility(View.GONE);
                mHolder.mContentImgThreeImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 4
         */
        if (itemViewType == 4) {
            final Four mHolder = (Four) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOneImg);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwoImg);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThreeImg);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFourImg);
                }

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
                mHolder.mContentImgTwoImg.setVisibility(View.GONE);
                mHolder.mContentImgThreeImg.setVisibility(View.GONE);
                mHolder.mContentImgFourImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 5
         */
        if (itemViewType == 5) {
            final Five mHolder = (Five) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions()
                                    .centerCrop()
                                    .placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOneImg);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))

                            .into(mHolder.mContentImgTwoImg);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThreeImg);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFourImg);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFiveImg);
                }

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
                mHolder.mContentImgTwoImg.setVisibility(View.GONE);
                mHolder.mContentImgThreeImg.setVisibility(View.GONE);
                mHolder.mContentImgFourImg.setVisibility(View.GONE);
                mHolder.mContentImgFiveImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 6
         */
        if (itemViewType == 6) {
            final Six mHolder = (Six) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOneImg);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwoImg);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThreeImg);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFourImg);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFiveImg);
                }

                //page six
                if (!TextUtils.isEmpty(split[5])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[5]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSixImg);
                }

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
                mHolder.mContentImgTwoImg.setVisibility(View.GONE);
                mHolder.mContentImgThreeImg.setVisibility(View.GONE);
                mHolder.mContentImgFourImg.setVisibility(View.GONE);
                mHolder.mContentImgFiveImg.setVisibility(View.GONE);
                mHolder.mContentImgSixImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 7
         */
        if (itemViewType == 7) {
            final Seven mHolder = (Seven) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOneImg);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwoImg);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThreeImg);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFourImg);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFiveImg);
                }

                //page six
                if (!TextUtils.isEmpty(split[5])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[5]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSixImg);
                }
                //page seven
                if (!TextUtils.isEmpty(split[6])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[6]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSevenImg);
                }

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
                mHolder.mContentImgTwoImg.setVisibility(View.GONE);
                mHolder.mContentImgThreeImg.setVisibility(View.GONE);
                mHolder.mContentImgFourImg.setVisibility(View.GONE);
                mHolder.mContentImgFiveImg.setVisibility(View.GONE);
                mHolder.mContentImgSixImg.setVisibility(View.GONE);
                mHolder.mContentImgSevenImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 8
         */
        if (itemViewType == 8) {
            final Eight mHolder = (Eight) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOneImg);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwoImg);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThreeImg);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFourImg);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFiveImg);
                }

                //page six
                if (!TextUtils.isEmpty(split[5])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[5]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSixImg);
                }
                //page seven
                if (!TextUtils.isEmpty(split[6])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[6]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSevenImg);
                }
                //page eight
                if (!TextUtils.isEmpty(split[7])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[7]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgEightImg);
                }

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
                mHolder.mContentImgTwoImg.setVisibility(View.GONE);
                mHolder.mContentImgThreeImg.setVisibility(View.GONE);
                mHolder.mContentImgFourImg.setVisibility(View.GONE);
                mHolder.mContentImgFiveImg.setVisibility(View.GONE);
                mHolder.mContentImgSixImg.setVisibility(View.GONE);
                mHolder.mContentImgSevenImg.setVisibility(View.GONE);
                mHolder.mContentImgEightImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        /**
         * 9
         */
        if (itemViewType == 9) {
            final Nine mHolder = (Nine) holder;

            //portrait
            String avaterUrl = trendsBean.getDynamicUserAvaterUrl();
            if (avaterUrl != null && avaterUrl.length() != 0) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(avaterUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp)
                        )
                        .into(mHolder.mPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mNameTv.setText(userName);
            } else {
                mHolder.mNameTv.setText("这是个没名字的家伙");
            }

            //date
            String publishTime = trendsBean.getPublishTime();
            if (!TextUtils.isEmpty(publishTime)) {
                mHolder.mDateTv.setText(publishTime.replace("T", " "));
            } else {
                mHolder.mDateTv.setText("不知道啥时候");
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content img
            /*********************************init img start*****************************************/
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOneImg);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwoImg);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThreeImg);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFourImg);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFiveImg);
                }

                //page six
                if (!TextUtils.isEmpty(split[5])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[5]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSixImg);
                }
                //page seven
                if (!TextUtils.isEmpty(split[6])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[6]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSevenImg);
                }
                //page eight
                if (!TextUtils.isEmpty(split[7])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[7]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgEightImg);
                }
                //page nine
                if (!TextUtils.isEmpty(split[8])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[8]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgNineImg);
                }

            } else {
                //图片为空
                mHolder.mContentImgOneImg.setVisibility(View.GONE);
                mHolder.mContentImgTwoImg.setVisibility(View.GONE);
                mHolder.mContentImgThreeImg.setVisibility(View.GONE);
                mHolder.mContentImgFourImg.setVisibility(View.GONE);
                mHolder.mContentImgFiveImg.setVisibility(View.GONE);
                mHolder.mContentImgSixImg.setVisibility(View.GONE);
                mHolder.mContentImgSevenImg.setVisibility(View.GONE);
                mHolder.mContentImgEightImg.setVisibility(View.GONE);
                mHolder.mContentImgNineImg.setVisibility(View.GONE);
            }
            /*********************************init img end*****************************************/

            //circle tag
            GetDynamicResultBean.TrendsBean.CircleBean circle = trendsBean.getCircle();
            if (circle != null) {
                int circleId = circle.getCircleId();
                if (circleId != 0) {
                    //有圈子
                    mHolder.mCircleTag.setVisibility(View.VISIBLE);
                    mHolder.mCircleTagNameTv.setText(circle.getCircleName());
                    mHolder.mCircleTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onCircleTagClick(trendsBean);
                            }
                        }
                    });

                } else {
                    mHolder.mCircleTag.setVisibility(View.GONE);
                }
            } else {
                mHolder.mCircleTag.setVisibility(View.GONE);
            }
            //like img

            //like count
            final int likeCount = trendsBean.getLikeCount();
            mHolder.mLikeCountTv.setText(likeCount + "");

            //comment img
            //comment count
            int commentCount = trendsBean.getCommentCount();
            mHolder.mCommentCountTv.setText(commentCount + "");

            /*listener*/
            //item
            mHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getmList().get(position));
                    }
                }
            });

            //like
            mHolder.mLikeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        mHolder.mLikeImg.setImageResource(R.drawable.like_after);
                        int newCount = likeCount + 1;
                        mHolder.mLikeCountTv.setText(newCount + "");
                        listener.onLikeClick(position);
                    }
                }
            });
            //comment
            mHolder.mCommentImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCommentClick();
                    }
                }
            });
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener != null) {
                    listener.onLongItemClick(getmList().get(position), position);
                }
                return true;
            }
        });
    }

    private TrendItemClick listener;

    public void setListener(TrendItemClick listener) {
        this.listener = listener;
    }

    public interface TrendItemClick {
        void onItemClick(GetDynamicResultBean.TrendsBean trendsBean);

        void onLikeClick(int position);

        void onCommentClick();

        void onCircleTagClick(GetDynamicResultBean.TrendsBean trendsBean);

        void onLongItemClick(GetDynamicResultBean.TrendsBean trendsBean, int position);
    }

    /**
     * img count: 0
     */
    static class Zero extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Zero(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    /**
     * img count: 1
     */
    static class One extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        One(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    /**
     * img count: 2
     */
    static class Two extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_two)
        ImageView mContentImgTwoImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Two(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    /**
     * img count: 3
     */
    static class Three extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_two)
        ImageView mContentImgTwoImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_three)
        ImageView mContentImgThreeImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Three(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    /**
     * img count: 4
     */
    static class Four extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_two)
        ImageView mContentImgTwoImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_three)
        ImageView mContentImgThreeImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_four)
        ImageView mContentImgFourImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Four(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    /**
     * img count: 5
     */
    static class Five extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_two)
        ImageView mContentImgTwoImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_three)
        ImageView mContentImgThreeImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_four)
        ImageView mContentImgFourImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_five)
        ImageView mContentImgFiveImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Five(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    /**
     * img count: 6
     */
    static class Six extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_two)
        ImageView mContentImgTwoImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_three)
        ImageView mContentImgThreeImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_four)
        ImageView mContentImgFourImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_five)
        ImageView mContentImgFiveImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_six)
        ImageView mContentImgSixImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Six(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    /**
     * img count: 7
     */
    static class Seven extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_two)
        ImageView mContentImgTwoImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_three)
        ImageView mContentImgThreeImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_four)
        ImageView mContentImgFourImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_five)
        ImageView mContentImgFiveImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_six)
        ImageView mContentImgSixImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_seven)
        ImageView mContentImgSevenImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Seven(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }


    /**
     * img count: 8
     */
    static class Eight extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_two)
        ImageView mContentImgTwoImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_three)
        ImageView mContentImgThreeImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_four)
        ImageView mContentImgFourImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_five)
        ImageView mContentImgFiveImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_six)
        ImageView mContentImgSixImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_seven)
        ImageView mContentImgSevenImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_eight)
        ImageView mContentImgEightImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Eight(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

    /**
     * img count: 9
     */
    static class Nine extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageOne_rcyItem_portrait_img)
        ImageView mPortraitImg;
        @BindView(R.id.home_pageOne_rcyItem_name_tv)
        TextView mNameTv;
        @BindView(R.id.home_pageOne_rcyItem_date_tv)
        TextView mDateTv;
        @BindView(R.id.home_pageOne_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageOne_rcyItem_content_img_one)
        ImageView mContentImgOneImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_two)
        ImageView mContentImgTwoImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_three)
        ImageView mContentImgThreeImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_four)
        ImageView mContentImgFourImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_five)
        ImageView mContentImgFiveImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_six)
        ImageView mContentImgSixImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_seven)
        ImageView mContentImgSevenImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_eight)
        ImageView mContentImgEightImg;
        @BindView(R.id.home_pageOne_rcyItem_content_img_nine)
        ImageView mContentImgNineImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageOne_rcyItem_circleTag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageOne_rcyItem_circleTag)
        LinearLayout mCircleTag;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        View view;

        Nine(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}
