package cn.cuit.microcollege.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.PersonDetailEditActivityContract;
import cn.cuit.microcollege.presenter.PersonDetailEditActivityPresenter;
import cn.cuit.microcollege.utils.ResourceUtil;
import cn.cuit.microcollege.widget.CustomPersonDetailGenderSelectDialog;
import cn.cuit.microcollege.widget.actionBar.CustomPublishActionBar;

public class PersonDetailEditActivity extends BaseActivity<PersonDetailEditActivityPresenter> implements PersonDetailEditActivityContract.View, CustomPublishActionBar.setBarListener, CustomPersonDetailGenderSelectDialog.ContentClickListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.activity_person_detail_edit_actionbar)
    CustomPublishActionBar mActionbar;
    @BindView(R.id.activity_person_detail_edit_bg_img)
    ImageView mBgImg;
    @BindView(R.id.activity_person_detail_edit_portrait_img)
    ImageView mPortraitImg;
    @BindView(R.id.activity_person_detail_edit_name_et)
    EditText mNameEt;
    @BindView(R.id.activity_person_detail_edit_sign_tv)
    EditText mSignTv;
    @BindView(R.id.activity_person_detail_edit_gender_tv)
    TextView mGenderTv;
    @BindView(R.id.activity_person_detail_edit_gender_cardv)
    CardView mGenderCardv;
    @BindView(R.id.activity_person_detail_edit_birth_tv)
    TextView mBirthTv;
    @BindView(R.id.activity_person_detail_edit_birth_cardv)
    CardView mBirthCardv;
    @BindView(R.id.activity_person_detail_edit_place_tv)
    EditText mPlaceTv;
    @BindView(R.id.activity_person_detail_edit_college_et)
    EditText mCollegeEt;
    @BindView(R.id.activity_person_detail_edit_job_et)
    EditText mJobEt;

    private static final int REQUEST_CODE_PORTRIAT = 0;
    private static final int REQUEST_CODE_BACKGROUNG = 1;
    private CustomPersonDetailGenderSelectDialog mGenderDialog;
    private DatePickerDialog mDataPickDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail_edit);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDialog() {
        super.initDialog();
        mGenderDialog = new CustomPersonDetailGenderSelectDialog(this);
        Calendar instance = Calendar.getInstance();
        mDataPickDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, this, instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public PersonDetailEditActivityPresenter initPresenter() {
        return new PersonDetailEditActivityPresenter(this);
    }

    @Override
    public void bindListener() {
        mActionbar.setBarListener(this);
        mGenderDialog.setContentClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PORTRIAT:
                if (resultCode == RESULT_OK) {
                    Uri portraitData = data.getData();
                    if (portraitData != null) {
                        String realPathFromURI = ResourceUtil.getRealPathFromURI(portraitData, this);
                        if (realPathFromURI != null) {
                            //save in to loal - realPathFromURI
//                            toast("地址：" + realPathFromURI, true);
                            //set
                            Glide.with(this)
                                    .load(portraitData)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                            .override(300, 300)
                                            .error(R.drawable.error))
                                    .into(mPortraitImg);
                        } else {
                            toast("图片地址获取失败", true);
                        }
                    }
                }
                break;
            case REQUEST_CODE_BACKGROUNG:
                if (resultCode == RESULT_OK) {
                    Uri portraitData = data.getData();
                    if (portraitData != null) {
                        String realPathFromURI = ResourceUtil.getRealPathFromURI(portraitData, this);
                        if (realPathFromURI != null) {
                            //save in to loal - realPathFromURI
                            toast("地址：" + realPathFromURI, true);
                            //set
                            Glide.with(this)
                                    .load(portraitData)
                                    .apply(new RequestOptions()
                                            .centerCrop()
                                            .error(R.drawable.error)
                                    )
                                    .into(mBgImg);
                        } else {
                            toast("图片地址获取失败", true);
                        }
                    }
                }
                break;
        }
    }

    @OnClick({R.id.activity_person_detail_edit_gender_cardv, R.id.activity_person_detail_edit_birth_cardv, R.id.activity_person_detail_edit_bg_img, R.id.activity_person_detail_edit_portrait_img})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.activity_person_detail_edit_bg_img:
                getImage(REQUEST_CODE_BACKGROUNG);
                break;
            case R.id.activity_person_detail_edit_portrait_img:
                getImage(REQUEST_CODE_PORTRIAT);

                break;
            case R.id.activity_person_detail_edit_gender_cardv:
                mGenderDialog.show();
                break;
            case R.id.activity_person_detail_edit_birth_cardv:
                mDataPickDialog.show();
                Window window = mDataPickDialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                break;
        }
    }

    private void getImage(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    /***************************************action bar call back********************************************/
    @Override
    public void onBack() {
        finish();
    }

    @Override
    public void onPublish() {
        //publish
        toast("执行保存逻辑", false);
        //finish
        finish();
    }

    /***************************************gender click listener******************************************/
    @Override
    public void onDialogItemOne() {
        mGenderTv.setText("男");
    }

    @Override
    public void onDialogItemTwo() {
        mGenderTv.setText("女");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuffer birthday = new StringBuffer();
        birthday.append(year + "-");
        birthday.append(month + 1 + "-");
        birthday.append(dayOfMonth + "");
        mBirthTv.setText(birthday.toString());
    }
}
