package com.lihang.leopro.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lihang.leopro.R;
import com.lihang.leopro.utils.TimeUtils;


/**
 * Created by leo
 * on 2016/4/18.
 * 添加头部和多布局的 recycleView的adapter
 */
public class HeadAndMutiAdapter  {//extends RecyclerView.Adapter<RecyclerView.ViewHolder>


//    private Context context;
//    private List<TuiMyFocusBean> myfacusList;
//    private LayoutInflater inflater;
//    private View.OnClickListener listener;
//
//    private int phoneWith;
//
//
//    /**
//     * recycleView添加头部
//     */
//
//    public static final int TYPE_HEADER = 0;
//
//    public static final int ZIXUN_TYPE_1 = 1;//资讯一张图
//    public static final int ZIXUN_TYPE_2 = 2;//资讯 2
//    public static final int ZIXUN_TYPE_3 = 3;//资讯 3
//    public static final int ZIXUN_TYPE_4 = 4;//资讯 4张图
//    public static final int ZIXUN_TYPE_5 = 5;//资讯 5张图
//    public static final int BOARD_TYPE = 6;//来自留言板
//    public static final int QUESTION_TYPE = 7;//来自问答
//    public static final int IMPRESS_TYPE = 8;//来自印象评价
//    public static final int PERSONMESSAGE_TYPE = 9;//来自资料
//    public static final int TYPE_NODATA = 10;
//    private View mHeaderView;
//
//
//    public void setHeaderView(View headerView) {
//        mHeaderView = headerView;
//        notifyItemInserted(0);
//    }
//
//    public View getmHeaderView() {
//        return mHeaderView;
//    }
//
//    public void setHeaderViewNull() {
//        mHeaderView = null;
//        notifyDataSetChanged();
//    }
//
//
//    @Override
//    public int getItemViewType(int position) {
//        if (mHeaderView == null) {
//            if (myfacusList.get(position).getCode().equals("similarFace")) {//相似脸资讯
//                if (myfacusList.get(position).getData().getPicList().size() == 1) {
//                    return ZIXUN_TYPE_1;
//                } else if (myfacusList.get(position).getData().getPicList().size() == 2) {
//                    return ZIXUN_TYPE_2;
//                } else if (myfacusList.get(position).getData().getPicList().size() == 3) {
//                    return ZIXUN_TYPE_3;
//                } else if (myfacusList.get(position).getData().getPicList().size() == 4) {
//                    return ZIXUN_TYPE_4;
//                }
//                {
//                    return ZIXUN_TYPE_5;
//                }
//            } else if (myfacusList.get(position).getCode().equals("board")) {
//                return BOARD_TYPE;//留言板
//            } else if (myfacusList.get(position).getCode().equals("askFace")) {
//                return QUESTION_TYPE;
//            } else if (myfacusList.get(position).getCode().equals("friendTag")) {
//                return IMPRESS_TYPE;//印象评价
//            } else if (myfacusList.get(position).getCode().equals("userInfo")) {
//                return PERSONMESSAGE_TYPE;//名片更新
//            } else {
//                //没有数据
//                return TYPE_NODATA;
//            }
//
//
//        } else {
//            if (position == 0) {
//                return TYPE_HEADER;
//            } else {
//                if (myfacusList.get(position - 1).getCode().equals("similarFace")) {//相似脸资讯
//                    if (myfacusList.get(position - 1).getData().getPicList().size() == 1) {
//                        return ZIXUN_TYPE_1;
//                    } else if (myfacusList.get(position - 1).getData().getPicList().size() == 2) {
//                        return ZIXUN_TYPE_2;
//                    } else if (myfacusList.get(position - 1).getData().getPicList().size() == 3) {
//                        return ZIXUN_TYPE_3;
//                    } else if (myfacusList.get(position - 1).getData().getPicList().size() == 4) {
//                        return ZIXUN_TYPE_4;
//                    } else {
//                        return ZIXUN_TYPE_5;
//                    }
//                } else if (myfacusList.get(position - 1).getCode().equals("board")) {
//                    return BOARD_TYPE;//留言板
//                } else if (myfacusList.get(position - 1).getCode().equals("askFace")) {
//                    return QUESTION_TYPE;
//                } else if (myfacusList.get(position - 1).getCode().equals("friendTag")) {
//                    return IMPRESS_TYPE;//印象评价
//                } else if (myfacusList.get(position - 1).getCode().equals("userInfo")) {
//                    return PERSONMESSAGE_TYPE;//名片更新
//                } else {
//                    //没有数据
//                    return TYPE_NODATA;
//                }
//
//            }
//        }
//    }
//
//
//    public HeadAndMutiAdapter(Context context, View.OnClickListener listener) {
//        this.context = context;
//        inflater = LayoutInflater.from(context);
//        this.listener = listener;
//
//        phoneWith = UIUtil.getWidth();
//    }
//
//    public void setTipoffList(ArrayList<TuiMyFocusBean> myfacusList) {
//        this.myfacusList = myfacusList;
//    }
//
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (mHeaderView != null && viewType == TYPE_HEADER) return new ImageViewHolder(mHeaderView);
//
//        RecyclerView.ViewHolder holder = null;
//        if (viewType == TYPE_NODATA) {
//            View v = inflater.inflate(R.layout.item_notipdatas_new_ding, parent, false);
//            holder = new NoDataHolder(v);
//        } else if (viewType == BOARD_TYPE) {//6 来自留言板
//            View v = inflater.inflate(R.layout.item_myfocuspeople_board, parent, false);
//            holder = new ImageViewHolder(v);
//        } else if (viewType == QUESTION_TYPE) {//来自问答
//            View v = inflater.inflate(R.layout.item_myfocuspeople_question, parent, false);
//            holder = new QuestionHolder(v);
//        } else if (viewType == IMPRESS_TYPE) {//来自印象评价
//            View v = inflater.inflate(R.layout.item_myfocuspeople_impress, parent, false);
//            holder = new ImpressHolder(v);
//        } else if (viewType == PERSONMESSAGE_TYPE) {//来自资料
//            View v = inflater.inflate(R.layout.item_myfocuspeople_personmessage, parent, false);
//            holder = new PersonMessageHolder(v);
//        } else if (viewType == ZIXUN_TYPE_1) {
//            View v = inflater.inflate(R.layout.item_myfocuspeople_zixun_1, parent, false);
//            holder = new ZiXunOneHolder(v);
//        } else if (viewType == ZIXUN_TYPE_2) {
//            View v = inflater.inflate(R.layout.item_myfocuspeople_zixun_2, parent, false);
//            holder = new ZiXunTwoHolder(v);
//        } else if (viewType == ZIXUN_TYPE_3) {
//            View v = inflater.inflate(R.layout.item_myfocuspeople_zixun_3, parent, false);
//            holder = new ZiXunThreeHolder(v);
//        } else if (viewType == ZIXUN_TYPE_4) {
//            View v = inflater.inflate(R.layout.item_myfocuspeople_zixun_4, parent, false);
//            holder = new ZiXunFourHolder(v);
//        } else if (viewType == ZIXUN_TYPE_5) {
//            View v = inflater.inflate(R.layout.item_myfocuspeople_zixun_5, parent, false);
//            holder = new ZiXunFiveHolder(v);
//        }
//        return holder;
//    }
//
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        if (getItemViewType(position) == TYPE_HEADER) return;
//        final int pos = getRealPosition(holder);
//        TuiMyFocusBean itemBean = myfacusList.get(pos);
//
//        if (holder instanceof ImageViewHolder) {//留言板
//            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(imageViewHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                imageViewHolder.text_name.setText("佚名");
//            } else {
//                imageViewHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//            imageViewHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//
//
//            if (itemBean.getData() != null && itemBean.getData().getFsLableInfo() != null) {
//                Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getData().getFsLableInfo().getHeadUrl())
//                        .bitmapTransform(new CropCircleTransformation(context))
//                        .dontAnimate()
//                        .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                        .into(imageViewHolder.image_bottom_head);
//                imageViewHolder.text_bottom_name.setText(itemBean.getData().getFsLableInfo().getNickName());
//                imageViewHolder.text_content.setText(itemBean.getData().getContent());
//            }
//
//
//            imageViewHolder.image_head.setTag(R.id.image_head, itemBean);
//            imageViewHolder.image_head.setOnClickListener(listener);
//            imageViewHolder.relative_board.setTag(itemBean);
//            imageViewHolder.relative_board.setOnClickListener(listener);
//
//
//        } else if (holder instanceof QuestionHolder) {//来自问答
//
//
//            QuestionHolder questionHolder = (QuestionHolder) holder;
//            LinearLayout.LayoutParams gvParams = (LinearLayout.LayoutParams) questionHolder.image_content.getLayoutParams();
//            gvParams.width = phoneWith / 3;
//            gvParams.height = phoneWith / 3;
//
//
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(questionHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                questionHolder.text_name.setText("佚名");
//            } else {
//                questionHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//
//            questionHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getFsMessagePicture().getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(questionHolder.image_content);
//            questionHolder.text_content.setText(itemBean.getData().getTitle());
//
//            if (itemBean.getData().getFsMessageComment() != null) {
//                questionHolder.text_answer.setText(itemBean.getData().getFsMessageComment().getFromUserName() + " : " + itemBean.getData().getFsMessageComment().getContent());
//            } else {
//                questionHolder.text_answer.setText("暂无回答");
//            }
//
//
//            questionHolder.image_head.setTag(R.id.image_head, itemBean);
//            questionHolder.image_head.setOnClickListener(listener);
//            questionHolder.linear_question_bgClick.setTag(itemBean);
//            questionHolder.linear_question_bgClick.setOnClickListener(listener);
//            questionHolder.relative_myfocus_more.setTag(itemBean);
//            questionHolder.relative_myfocus_more.setOnClickListener(listener);
//
//
//        } else if (holder instanceof ImpressHolder) {//来自印象评价
//
//            ImpressHolder impressHolder = (ImpressHolder) holder;
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(impressHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                impressHolder.text_name.setText("佚名");
//            } else {
//                impressHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//            impressHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//
//
//            if (itemBean.getData() != null && itemBean.getData().getFsLableInfo() != null) {
//                Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getData().getFsLableInfo().getHeadUrl())
//                        .bitmapTransform(new CropCircleTransformation(context))
//                        .dontAnimate()
//                        .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                        .into(impressHolder.image_bottom_head);
//                impressHolder.text_bottom_name.setText(itemBean.getData().getFsLableInfo().getNickName());
//                impressHolder.text_content.setText("#" + itemBean.getData().getTitle() + "#");
//            }
//
//
//            impressHolder.image_head.setTag(R.id.image_head, itemBean);
//            impressHolder.image_head.setOnClickListener(listener);
//
//            impressHolder.relative_impress_tagt.setTag(itemBean);
//            impressHolder.relative_impress_tagt.setOnClickListener(listener);
//
//        } else if (holder instanceof PersonMessageHolder) {//来自 资料修改
//            PersonMessageHolder personMessageHolder = (PersonMessageHolder) holder;
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(personMessageHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                personMessageHolder.text_name.setText("佚名");
//            } else {
//                personMessageHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//
//            personMessageHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//
//            if (TextUtils.isEmpty(itemBean.getData().getNickName())) {
//                personMessageHolder.text_bottom_name.setText("姓名 : " + "佚名");
//            } else {
//                personMessageHolder.text_bottom_name.setText("姓名 : " + itemBean.getData().getNickName());
//            }
//
//
//            if (TextUtils.isEmpty(itemBean.getData().getBirthday())) {
//                personMessageHolder.text_birthday.setText("生日 : " + "待完善");
//            } else {
//                personMessageHolder.text_birthday.setText("生日 : " + itemBean.getData().getBirthday());
//            }
//
//            if (TextUtils.isEmpty(itemBean.getData().getOccupation())) {
//
//                personMessageHolder.text_vacation.setText("职业 : " + "待完善");
//            } else {
//
//                personMessageHolder.text_vacation.setText("职业 : " + itemBean.getData().getOccupation());
//            }
//
//
//            if (TextUtils.isEmpty(itemBean.getData().getIntroduce())) {
//
//                personMessageHolder.text_life.setText("履历 : " + "待完善");
//            } else {
//
//                personMessageHolder.text_life.setText("履历 : " + itemBean.getData().getIntroduce());
//
//            }
//
//
//            personMessageHolder.image_head.setTag(R.id.image_head, itemBean);
//            personMessageHolder.image_head.setOnClickListener(listener);
//            personMessageHolder.relative_person_bgClick.setTag(itemBean);
//            personMessageHolder.relative_person_bgClick.setOnClickListener(listener);
//
//
//        } else if (holder instanceof ZiXunOneHolder) {//资讯里 1 张图
//            ZiXunOneHolder ziXunOneHolder = (ZiXunOneHolder) holder;
//            RelativeLayout.LayoutParams exParams = (RelativeLayout.LayoutParams) ziXunOneHolder.ExpLayout.getLayoutParams();
//            exParams.height = UIUtil.getWidth() * 9 / 16;
//
//            /**
//             * 解决glide在列表recycleView，刷新列表屏幕，闪一下。第一禁止recycleView的动画。第二解决加上target去解决
//             */
//            String urlTarget = (String) ziXunOneHolder.ExpLayout.getTag(R.id.ExpLayout);
//            String currentUrl = SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(0).getPic_url();
//            if (!currentUrl.equals(urlTarget)) {
//                ImageMessageBean imageMessageBean = itemBean.getData().getPicList().get(0);
//                if (imageMessageBean.getFaceList().size() > 0) {
//                    ziXunOneHolder.ExpLayout.setImgBg(imageMessageBean.getSizeW(), imageMessageBean.getSizeH(), itemBean.getData().getPicList().get(0).getFaceList().get(0).getSourcePic(), imageMessageBean.getFaceList().get(0).getUpLeftX(), imageMessageBean.getFaceList().get(0).getUpLeftY(), imageMessageBean.getFaceList().get(0).getDownRightX(), imageMessageBean.getFaceList().get(0).getDownRightY());
//                } else {
//                    //图片上没有脸的情况
//                    ziXunOneHolder.ExpLayout.setImgBg(imageMessageBean.getSizeW(), imageMessageBean.getSizeH(), imageMessageBean.getPic_url());
//                }
//                ziXunOneHolder.ExpLayout.setTag(R.id.ExpLayout, SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(0).getFaceList().get(0).getSourcePic());
//
//            }
//
//
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(ziXunOneHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                ziXunOneHolder.text_name.setText("佚名");
//            } else {
//                ziXunOneHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//
//
//            ziXunOneHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//            ziXunOneHolder.text_content.setText(itemBean.getData().getTitle());
//
//            ziXunOneHolder.image_head.setTag(R.id.image_head, itemBean);
//            ziXunOneHolder.image_head.setOnClickListener(listener);
//
//            ziXunOneHolder.relative_zixun_bg.setTag(itemBean);
//            ziXunOneHolder.relative_zixun_bg.setOnClickListener(listener);
//            ziXunOneHolder.relative_myfocus_more.setTag(itemBean);
//            ziXunOneHolder.relative_myfocus_more.setOnClickListener(listener);
//
//        } else if (holder instanceof ZiXunTwoHolder) { //资讯里2张图
//            ZiXunTwoHolder ziXunTwoHolder = (ZiXunTwoHolder) holder;
//            RelativeLayout.LayoutParams ras = (RelativeLayout.LayoutParams) ziXunTwoHolder.linear_image_container.getLayoutParams();
//            ras.height = UIUtil.getWidth() * 6 / 21;
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(0).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunTwoHolder.image_1);
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(1).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunTwoHolder.image_2);
//
//
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(ziXunTwoHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                ziXunTwoHolder.text_name.setText("佚名");
//            } else {
//                ziXunTwoHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//
//            ziXunTwoHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//            ziXunTwoHolder.text_content.setText(itemBean.getData().getTitle());
//
//            ziXunTwoHolder.image_head.setTag(R.id.image_head, itemBean);
//            ziXunTwoHolder.image_head.setOnClickListener(listener);
//            ziXunTwoHolder.relative_zixun_bg.setTag(itemBean);
//            ziXunTwoHolder.relative_zixun_bg.setOnClickListener(listener);
//
//            ziXunTwoHolder.relative_myfocus_more.setTag(itemBean);
//            ziXunTwoHolder.relative_myfocus_more.setOnClickListener(listener);
//
//        } else if (holder instanceof ZiXunThreeHolder) {//资讯3张图
//            ZiXunThreeHolder ziXunThreeHolder = (ZiXunThreeHolder) holder;
//            RelativeLayout.LayoutParams ras = (RelativeLayout.LayoutParams) ziXunThreeHolder.linear_image_container.getLayoutParams();
//            ras.height = UIUtil.getWidth() * 8 / 21;
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(0).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunThreeHolder.image_1);
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(1).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunThreeHolder.image_2);
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(2).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunThreeHolder.image_3);
//
//
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(ziXunThreeHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                ziXunThreeHolder.text_name.setText("佚名");
//            } else {
//                ziXunThreeHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//
//
//            ziXunThreeHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//            ziXunThreeHolder.text_content.setText(itemBean.getData().getTitle());
//
//
//            ziXunThreeHolder.image_head.setTag(R.id.image_head, itemBean);
//            ziXunThreeHolder.image_head.setOnClickListener(listener);
//            ziXunThreeHolder.relative_zixun_bg.setTag(itemBean);
//            ziXunThreeHolder.relative_zixun_bg.setOnClickListener(listener);
//
//            ziXunThreeHolder.relative_myfocus_more.setTag(itemBean);
//            ziXunThreeHolder.relative_myfocus_more.setOnClickListener(listener);
//
//        } else if (holder instanceof ZiXunFourHolder) {
//            ZiXunFourHolder ziXunFourHolder = (ZiXunFourHolder) holder;
//            RelativeLayout.LayoutParams ras = (RelativeLayout.LayoutParams) ziXunFourHolder.linear_image_container.getLayoutParams();
//            ras.height = UIUtil.getWidth() * 12 / 21;
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(0).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFourHolder.image_1);
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(1).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFourHolder.image_2);
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(2).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFourHolder.image_3);
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(3).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFourHolder.image_4);
//
//
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(ziXunFourHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                ziXunFourHolder.text_name.setText("佚名");
//            } else {
//                ziXunFourHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//
//
//
//            ziXunFourHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//            ziXunFourHolder.text_content.setText(itemBean.getData().getTitle());
//
//
//            ziXunFourHolder.image_head.setTag(R.id.image_head, itemBean);
//            ziXunFourHolder.image_head.setOnClickListener(listener);
//            ziXunFourHolder.relative_zixun_bg.setTag(itemBean);
//            ziXunFourHolder.relative_zixun_bg.setOnClickListener(listener);
//
//            ziXunFourHolder.relative_myfocus_more.setTag(itemBean);
//            ziXunFourHolder.relative_myfocus_more.setOnClickListener(listener);
//
//        } else if (holder instanceof ZiXunFiveHolder) {
//            ZiXunFiveHolder ziXunFiveHolder = (ZiXunFiveHolder) holder;
//            RelativeLayout.LayoutParams ras = (RelativeLayout.LayoutParams) ziXunFiveHolder.linear_image_container.getLayoutParams();
//            ras.height = UIUtil.getWidth() * 2 / 3;
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(0).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFiveHolder.image_1);
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(1).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFiveHolder.image_2);
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(2).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFiveHolder.image_3);
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(3).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFiveHolder.image_4);
//
//
//            Glide.with(context)
//                    .load(SystemConst.IMAGE_HEAD + itemBean.getData().getPicList().get(4).getPicUrl())
//                    .asBitmap()
//                    .dontAnimate()
//                    .placeholder(R.drawable.newleo)
//                    .error(R.drawable.newleo)
//                    .into(ziXunFiveHolder.image_5);
//
//
//            Glide.with(context).load(SystemConst.IMAGE_HEAD + itemBean.getFsLableInfo().getHeadUrl())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .dontAnimate()
//                    .placeholder(R.mipmap.default_head).error(R.mipmap.default_head)
//                    .into(ziXunFiveHolder.image_head);
//
//
//            if (TextUtils.isEmpty(itemBean.getFsLableInfo().getNickName())) {
//                ziXunFiveHolder.text_name.setText("佚名");
//            } else {
//                ziXunFiveHolder.text_name.setText(itemBean.getFsLableInfo().getNickName());
//            }
//
//
//
//            ziXunFiveHolder.text_time.setText(TimeUtils.getTimesToNow(itemBean.getCreateTime()));
//            ziXunFiveHolder.text_content.setText(itemBean.getData().getTitle());
//
//            ziXunFiveHolder.image_head.setTag(R.id.image_head, itemBean);
//            ziXunFiveHolder.image_head.setOnClickListener(listener);
//            ziXunFiveHolder.relative_zixun_bg.setTag(itemBean);
//            ziXunFiveHolder.relative_zixun_bg.setOnClickListener(listener);
//
//            ziXunFiveHolder.relative_myfocus_more.setTag(itemBean);
//            ziXunFiveHolder.relative_myfocus_more.setOnClickListener(listener);
//        }
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        if (mHeaderView == null) {
//            return myfacusList == null ? 0 : myfacusList.size();
//        } else {
//            return myfacusList == null ? 0 : myfacusList.size() + 1;
//        }
//    }
//
//
//    public int getRealPosition(RecyclerView.ViewHolder holder) {
//        int position = holder.getLayoutPosition();
//        return mHeaderView == null ? position : position - 1;
//    }
//
//
//    class ImageViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//
//        ImageView image_bottom_head;//第二级 头像
//        TextView text_bottom_name;//第二级 名字
//        TextView text_content;//内容
//        RelativeLayout relative_board;
//
//        public ImageViewHolder(View itemView) {
//            super(itemView);
//            if (itemView == mHeaderView) return;
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//            image_bottom_head = itemView.findViewById(R.id.image_bottom_head);
//            text_bottom_name = itemView.findViewById(R.id.text_bottom_name);
//            text_content = itemView.findViewById(R.id.text_content);
//            relative_board = itemView.findViewById(R.id.relative_board);
//
//        }
//    }
//
//
//    class QuestionHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//        ImageView image_content;//内容图
//        TextView text_content;
//        TextView text_answer;
//
//        LinearLayout linear_question_bgClick;
//        RelativeLayout relative_myfocus_more;
//
//
//        public QuestionHolder(View itemView) {
//            super(itemView);
//            if (itemView == mHeaderView) return;
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//
//            image_content = itemView.findViewById(R.id.image_content);
//            text_content = itemView.findViewById(R.id.text_content);
//            text_answer = itemView.findViewById(R.id.text_answer);
//            linear_question_bgClick = itemView.findViewById(R.id.linear_question_bgClick);
//            relative_myfocus_more = itemView.findViewById(R.id.relative_myfocus_more);
//
//        }
//    }
//
//
//    class ImpressHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//
//        ImageView image_bottom_head;//第二级 头像
//        TextView text_bottom_name;//第二级 名字
//        TextView text_content;//内容
//        RelativeLayout relative_impress_tagt;
//
//        public ImpressHolder(View itemView) {
//            super(itemView);
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//            image_bottom_head = itemView.findViewById(R.id.image_bottom_head);
//            text_bottom_name = itemView.findViewById(R.id.text_bottom_name);
//            text_content = itemView.findViewById(R.id.text_content);
//            relative_impress_tagt = itemView.findViewById(R.id.relative_impress_tagt);
//
//        }
//    }
//
//
//    class PersonMessageHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//
//        TextView text_bottom_name;
//        TextView text_birthday;
//        TextView text_vacation;
//        TextView text_life;
//
//        RelativeLayout relative_person_bgClick;
//
//
//        public PersonMessageHolder(View itemView) {
//            super(itemView);
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//            text_bottom_name = itemView.findViewById(R.id.text_bottom_name);
//            text_birthday = itemView.findViewById(R.id.text_birthday);
//            text_vacation = itemView.findViewById(R.id.text_vacation);
//            text_life = itemView.findViewById(R.id.text_life);
//            relative_person_bgClick = itemView.findViewById(R.id.relative_person_bgClick);
//
//        }
//    }
//
//
//    class ZiXunOneHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//        ExpLayout ExpLayout;
//        TextView text_content;
//        RelativeLayout relative_zixun_bg;//背景点击
//        RelativeLayout relative_myfocus_more;//背景点击
//
//
//        public ZiXunOneHolder(View itemView) {
//            super(itemView);
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//
//            ExpLayout = itemView.findViewById(R.id.ExpLayout);
//            text_content = itemView.findViewById(R.id.text_content);
//            relative_zixun_bg = itemView.findViewById(R.id.relative_zixun_bg);
//            relative_myfocus_more = itemView.findViewById(R.id.relative_myfocus_more);
//
//        }
//    }
//
//
//    class ZiXunTwoHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//
//        ImageView image_1;
//        ImageView image_2;
//        TextView text_content;
//        LinearLayout linear_image_container;
//        RelativeLayout relative_zixun_bg;
//        RelativeLayout relative_myfocus_more;
//
//
//        public ZiXunTwoHolder(View itemView) {
//            super(itemView);
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//
//            image_1 = itemView.findViewById(R.id.image_1);
//            image_2 = itemView.findViewById(R.id.image_2);
//            text_content = itemView.findViewById(R.id.text_content);
//            linear_image_container = itemView.findViewById(R.id.linear_image_container);
//            relative_zixun_bg = itemView.findViewById(R.id.relative_zixun_bg);
//            relative_myfocus_more = itemView.findViewById(R.id.relative_myfocus_more);
//
//
//        }
//    }
//
//
//    class ZiXunThreeHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//
//        ImageView image_1;
//        ImageView image_2;
//        ImageView image_3;
//        TextView text_content;
//        LinearLayout linear_image_container;
//        RelativeLayout relative_zixun_bg;
//        RelativeLayout relative_myfocus_more;
//
//        public ZiXunThreeHolder(View itemView) {
//            super(itemView);
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//
//            image_1 = itemView.findViewById(R.id.image_1);
//            image_2 = itemView.findViewById(R.id.image_2);
//            image_3 = itemView.findViewById(R.id.image_3);
//            text_content = itemView.findViewById(R.id.text_content);
//            linear_image_container = itemView.findViewById(R.id.linear_image_container);
//            relative_zixun_bg = itemView.findViewById(R.id.relative_zixun_bg);
//            relative_myfocus_more = itemView.findViewById(R.id.relative_myfocus_more);
//
//
//        }
//    }
//
//
//    class ZiXunFourHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//
//        ImageView image_1;
//        ImageView image_2;
//        ImageView image_3;
//        ImageView image_4;
//        TextView text_content;
//        LinearLayout linear_image_container;
//        RelativeLayout relative_zixun_bg;
//        RelativeLayout relative_myfocus_more;
//
//
//        public ZiXunFourHolder(View itemView) {
//            super(itemView);
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//
//            image_1 = itemView.findViewById(R.id.image_1);
//            image_2 = itemView.findViewById(R.id.image_2);
//            image_3 = itemView.findViewById(R.id.image_3);
//            image_4 = itemView.findViewById(R.id.image_4);
//            text_content = itemView.findViewById(R.id.text_content);
//            linear_image_container = itemView.findViewById(R.id.linear_image_container);
//            relative_zixun_bg = itemView.findViewById(R.id.relative_zixun_bg);
//            relative_myfocus_more = itemView.findViewById(R.id.relative_myfocus_more);
//
//
//        }
//    }
//
//
//    class ZiXunFiveHolder extends RecyclerView.ViewHolder {
//
//        ImageView image_head;//第一级 头像
//        TextView text_name;//第一级 名字
//        TextView text_time;//时间
//        TextView text_flag;//标签
//
//
//        ImageView image_1;
//        ImageView image_2;
//        ImageView image_3;
//        ImageView image_4;
//        ImageView image_5;
//        TextView text_content;
//        LinearLayout linear_image_container;
//        RelativeLayout relative_zixun_bg;
//        RelativeLayout relative_myfocus_more;
//
//
//        public ZiXunFiveHolder(View itemView) {
//            super(itemView);
//            image_head = itemView.findViewById(R.id.image_head);
//            text_name = itemView.findViewById(R.id.text_name);
//            text_time = itemView.findViewById(R.id.text_time);
//            text_flag = itemView.findViewById(R.id.text_flag);
//
//            image_1 = itemView.findViewById(R.id.image_1);
//            image_2 = itemView.findViewById(R.id.image_2);
//            image_3 = itemView.findViewById(R.id.image_3);
//            image_4 = itemView.findViewById(R.id.image_4);
//            image_5 = itemView.findViewById(R.id.image_5);
//            text_content = itemView.findViewById(R.id.text_content);
//            linear_image_container = itemView.findViewById(R.id.linear_image_container);
//            relative_zixun_bg = itemView.findViewById(R.id.relative_zixun_bg);
//            relative_myfocus_more = itemView.findViewById(R.id.relative_myfocus_more);
//
//
//        }
//    }
//
//
//    class NoDataHolder extends RecyclerView.ViewHolder {
//        ImageView image_nodatas;//头像
//
//        public NoDataHolder(View itemView) {
//            super(itemView);
//            image_nodatas = itemView.findViewById(R.id.image_nodatas);
//        }
//    }


}
