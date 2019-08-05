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
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/23
 * @Descirption: home-fragment-pageOne(所有)
 */
public class HomePageTwoAdapter extends BaseRcyAdapter<GetDynamicWithCircleResultBean.TrendsBean, RecyclerView.ViewHolder> {

    public HomePageTwoAdapter() {
        super();
    }

    public HomePageTwoAdapter(List<GetDynamicWithCircleResultBean.TrendsBean> list) {

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
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_zero, parent, false);
            return new Zero(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_one, parent, false);
            return new One(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_two, parent, false);
            return new Two(inflate);
        } else if (viewType == 3) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_three, parent, false);
            return new Three(inflate);
        } else if (viewType == 4) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_four, parent, false);
            return new Four(inflate);
        } else if (viewType == 5) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_five, parent, false);
            return new Five(inflate);
        } else if (viewType == 6) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_six, parent, false);
            return new Six(inflate);
        } else if (viewType == 7) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_seven, parent, false);
            return new Seven(inflate);
        } else if (viewType == 8) {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_eight, parent, false);
            return new Eight(inflate);
        } else {
            View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_pagetwo_rcy_item_type_nine, parent, false);
            return new Nine(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int itemViewType = getItemViewType(position);
        Context myContext = MyApplication.getMyContext();
        final GetDynamicWithCircleResultBean.TrendsBean trendsBean = getmList().get(position);

        /***
         * //cirlce img
         * //circle name
         * //circle title
         *
         * //content text
         * //content imgs
         *
         * //user portrait
         * //user name
         *
         * //like img
         * //like text
         *
         * //comment img
         * //comment text
         *
         listener - circle tag
         listener - item
         listener - like
         listener - comment
         *
         */

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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs

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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });
        }

        /**
         * 1
         */
        if (itemViewType == 1) {//1布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                //page one
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(imageList))
                        .apply(new RequestOptions()
                                .centerCrop()
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mContentImgOne);

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }

        /**
         * 2
         */
        if (itemViewType == 2) {//2布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
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
                            .into(mHolder.mContentImgOne);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwo);
                }

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
                mHolder.mContentImgTwo.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }

        /**
         * 3
         */
        if (itemViewType == 3) {//布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOne);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwo);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThree);
                }

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
                mHolder.mContentImgTwo.setVisibility(View.GONE);
                mHolder.mContentImgThree.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }


        /**
         * 4
         */
        if (itemViewType == 4) {//布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOne);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwo);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThree);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFour);
                }

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
                mHolder.mContentImgTwo.setVisibility(View.GONE);
                mHolder.mContentImgThree.setVisibility(View.GONE);
                mHolder.mContentImgFour.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }

        /**
         * 5
         */
        if (itemViewType == 5) {//布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
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
                            .into(mHolder.mContentImgOne);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))

                            .into(mHolder.mContentImgTwo);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThree);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFour);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFive);
                }

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
                mHolder.mContentImgTwo.setVisibility(View.GONE);
                mHolder.mContentImgThree.setVisibility(View.GONE);
                mHolder.mContentImgFour.setVisibility(View.GONE);
                mHolder.mContentImgFive.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }

        /**
         * 6
         */
        if (itemViewType == 6) {//布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOne);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwo);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThree);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFour);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFive);
                }

                //page six
                if (!TextUtils.isEmpty(split[5])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[5]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSix);
                }

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
                mHolder.mContentImgTwo.setVisibility(View.GONE);
                mHolder.mContentImgThree.setVisibility(View.GONE);
                mHolder.mContentImgFour.setVisibility(View.GONE);
                mHolder.mContentImgFive.setVisibility(View.GONE);
                mHolder.mContentImgSix.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }

        /**
         * 7
         */
        if (itemViewType == 7) {//布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOne);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOne);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwo);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThree);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFive);
                }

                //page six
                if (!TextUtils.isEmpty(split[5])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[5]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSix);
                }
                //page seven
                if (!TextUtils.isEmpty(split[6])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[6]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSeven);
                }

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
                mHolder.mContentImgTwo.setVisibility(View.GONE);
                mHolder.mContentImgThree.setVisibility(View.GONE);
                mHolder.mContentImgFour.setVisibility(View.GONE);
                mHolder.mContentImgFive.setVisibility(View.GONE);
                mHolder.mContentImgSix.setVisibility(View.GONE);
                mHolder.mContentImgSeven.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }

        /**
         * 8
         */
        if (itemViewType == 8) {//布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOne);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwo);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThree);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFour);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFive);
                }

                //page six
                if (!TextUtils.isEmpty(split[5])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[5]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSix);
                }
                //page seven
                if (!TextUtils.isEmpty(split[6])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[6]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSeven);
                }
                //page eight
                if (!TextUtils.isEmpty(split[7])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[7]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgEight);
                }

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
                mHolder.mContentImgTwo.setVisibility(View.GONE);
                mHolder.mContentImgThree.setVisibility(View.GONE);
                mHolder.mContentImgFour.setVisibility(View.GONE);
                mHolder.mContentImgFive.setVisibility(View.GONE);
                mHolder.mContentImgSix.setVisibility(View.GONE);
                mHolder.mContentImgSeven.setVisibility(View.GONE);
                mHolder.mContentImgEight.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }

        /**
         * 9
         */
        if (itemViewType == 9) {//9布局
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
                        .into(mHolder.mUserPortraitImg);
            }

            //name
            String userName = trendsBean.getDynamicUserName();
            if (!TextUtils.isEmpty(userName)) {
                mHolder.mUserNameTv.setText(userName);
            } else {
                mHolder.mUserNameTv.setText("这是个没名字的家伙");
            }

            // circle name
            String circleName = trendsBean.getCircle().getCircleName();
            if (circleName != null) {
                mHolder.mCircleTagNameTv.setText(circleName);
            } else {
                mHolder.mCircleTagNameTv.setText("这个圈子很奇怪 没有名字");
            }

            // circle title
            String cricleTitle = trendsBean.getCircle().getCircleTitle();
            if (!TextUtils.isEmpty(cricleTitle)) {
                mHolder.mCircleTagTitleTv.setText(cricleTitle);
            } else {
                mHolder.mCircleTagTitleTv.setText("这个圈子很奇怪 没有标题");
            }

            //circle img
            String circleAvaterUrl = trendsBean.getCircle().getCircleAvaterUrl();
            if (!TextUtils.isEmpty(circleAvaterUrl)) {
                Glide.with(myContext)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(mHolder.mCircleTagImg);
            } else {//url is null
                Glide.with(myContext)
                        .load(R.drawable.logo_temp)
                        .into(mHolder.mCircleTagImg);
            }

            //content text
            String content = trendsBean.getDynamicContent();
            if (content != null) {
                mHolder.mContentTv.setVisibility(View.VISIBLE);
                mHolder.mContentTv.setText(TransFormUtil.getDecodeWithUTF(content));
            } else {
                mHolder.mContentTv.setVisibility(View.GONE);
            }

            //content imgs
            String imageList = trendsBean.getImageList();
            if (!TextUtils.isEmpty(imageList)) {
                String[] split = imageList.split(",");

                //page one
                if (!TextUtils.isEmpty(split[0])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[0]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgOne);
                }

                //page two
                if (!TextUtils.isEmpty(split[1])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[1]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgTwo);
                }

                //page three
                if (!TextUtils.isEmpty(split[2])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[2]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgThree);
                }

                //page four
                if (!TextUtils.isEmpty(split[3])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[3]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFour);
                }

                //page five
                if (!TextUtils.isEmpty(split[4])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[4]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgFive);
                }

                //page six
                if (!TextUtils.isEmpty(split[5])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[5]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSix);
                }
                //page seven
                if (!TextUtils.isEmpty(split[6])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[6]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgSeven);
                }
                //page eight
                if (!TextUtils.isEmpty(split[7])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[7]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgEight);
                }
                //page nine
                if (!TextUtils.isEmpty(split[8])) {
                    Glide.with(myContext)
                            .load(TransFormUtil.getLocalImageUrl(split[8]))
                            .apply(new RequestOptions().centerCrop().placeholder(R.drawable.logo_temp)
                                    .error(R.drawable.logo_temp))
                            .into(mHolder.mContentImgNine);
                }

            } else {
                //图片为空
                mHolder.mContentImgOne.setVisibility(View.GONE);
                mHolder.mContentImgTwo.setVisibility(View.GONE);
                mHolder.mContentImgThree.setVisibility(View.GONE);
                mHolder.mContentImgFour.setVisibility(View.GONE);
                mHolder.mContentImgFive.setVisibility(View.GONE);
                mHolder.mContentImgSix.setVisibility(View.GONE);
                mHolder.mContentImgSeven.setVisibility(View.GONE);
                mHolder.mContentImgEight.setVisibility(View.GONE);
                mHolder.mContentImgNine.setVisibility(View.GONE);
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

            /***
             listener - circle tag
             */
            mHolder.mCircleTagLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onCircleTagClick(getmList().get(mHolder.getAdapterPosition()));
                    }
                }
            });

            /**********type end*********************/
        }

    }

    private TrendItemClick listener;

    public void setListener(TrendItemClick listener) {
        this.listener = listener;
    }

    public interface TrendItemClick {
        void onItemClick(GetDynamicWithCircleResultBean.TrendsBean trendsBean);

        void onLikeClick(int position);

        void onCommentClick();

        void onCircleTagClick(GetDynamicWithCircleResultBean.TrendsBean trendsBean);
    }


    /**
     * img count: 0
     */
    static class Zero extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        View view;

        public Zero(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 1
     */
    static class One extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        View view;

        public One(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 2
     */
    static class Two extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_two)
        ImageView mContentImgTwo;
        View view;

        public Two(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 3
     */
    static class Three extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_two)
        ImageView mContentImgTwo;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_three)
        ImageView mContentImgThree;
        View view;

        public Three(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 4
     */
    static class Four extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_two)
        ImageView mContentImgTwo;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_three)
        ImageView mContentImgThree;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_four)
        ImageView mContentImgFour;
        View view;

        public Four(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 5
     */
    static class Five extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_two)
        ImageView mContentImgTwo;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_three)
        ImageView mContentImgThree;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_four)
        ImageView mContentImgFour;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_five)
        ImageView mContentImgFive;
        View view;

        public Five(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 6
     */
    static class Six extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_two)
        ImageView mContentImgTwo;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_three)
        ImageView mContentImgThree;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_four)
        ImageView mContentImgFour;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_five)
        ImageView mContentImgFive;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_six)
        ImageView mContentImgSix;
        View view;

        public Six(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 7
     */
    static class Seven extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_two)
        ImageView mContentImgTwo;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_three)
        ImageView mContentImgThree;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_four)
        ImageView mContentImgFour;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_five)
        ImageView mContentImgFive;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_six)
        ImageView mContentImgSix;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_seven)
        ImageView mContentImgSeven;
        View view;

        public Seven(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 8
     */
    static class Eight extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_two)
        ImageView mContentImgTwo;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_three)
        ImageView mContentImgThree;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_four)
        ImageView mContentImgFour;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_five)
        ImageView mContentImgFive;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_six)
        ImageView mContentImgSix;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_seven)
        ImageView mContentImgSeven;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_eight)
        ImageView mContentImgEight;
        View view;

        public Eight(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }

    /**
     * img count: 9
     */
    static class Nine extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_img)
        ImageView mCircleTagImg;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_name_tv)
        TextView mCircleTagNameTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag_date_tv)
        TextView mCircleTagTitleTv;
        @BindView(R.id.home_pageTwo_rcyItem_circle_tag)
        LinearLayout mCircleTagLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_tv)
        TextView mContentTv;
        @BindView(R.id.home_pageTwo_rcyItem_user_portrait_img)
        ImageView mUserPortraitImg;
        @BindView(R.id.home_pageTwo_rcyItem_user_name_tv)
        TextView mUserNameTv;
        @BindView(R.id.rcyItem_like_img)
        ImageView mLikeImg;
        @BindView(R.id.rcyItem_like_count_tv)
        TextView mLikeCountTv;
        @BindView(R.id.rcyItem_like_lyt)
        LinearLayout mLikeLyt;
        @BindView(R.id.rcyItem_comment_img)
        ImageView mCommentImg;
        @BindView(R.id.rcyItem_comment_count_tv)
        TextView mCommentCountTv;
        @BindView(R.id.rcyItem_comment_lyt)
        LinearLayout mCommentLyt;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_one)
        ImageView mContentImgOne;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_two)
        ImageView mContentImgTwo;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_three)
        ImageView mContentImgThree;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_four)
        ImageView mContentImgFour;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_five)
        ImageView mContentImgFive;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_six)
        ImageView mContentImgSix;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_seven)
        ImageView mContentImgSeven;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_eight)
        ImageView mContentImgEight;
        @BindView(R.id.home_pageTwo_rcyItem_content_img_nine)
        ImageView mContentImgNine;
        View view;

        public Nine(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }
}

