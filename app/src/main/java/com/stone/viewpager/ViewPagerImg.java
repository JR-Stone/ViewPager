package com.stone.viewpager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.stone.viewpager.apater.ClipPaddingAdp;
import com.stone.viewpager.base.DefaultBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : 佰路
 * @e-mail : bailu_manager@163.com
 * @date   : 2901-11-29
 * @other  :博客 https://blog.csdn.net/jason_rui
 */
public class ViewPagerImg extends DefaultBaseActivity {

    @BindView(R.id.clipPaddingVpg)
    ViewPager clipPaddingVpg;
    @BindView(R.id.clipChildrenVpg)
    ViewPager clipChildrenVpg;
    @BindView(R.id.playerVpg)
    ViewPager playerVpg;
    private List<String> listClipPadding;
    private List<String> listPlayer;
    private int position = 1;
    private boolean isAutoPlay = true;

    private Handler handler = new Handler();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void init(Bundle savedInstanceState) {
        getClipPaddingData();
        ClipPaddingAdp viewPagerAdp = new ClipPaddingAdp(this, listClipPadding);
        /*
         * ========== clipToPadding+pageTransformer ============*/
        //缓存个数
        clipPaddingVpg.setOffscreenPageLimit(3);
        //设置显示效果 缩放 透明度
        clipPaddingVpg.setPageTransformer(false, new OutPageTransformerClipPd());
        clipPaddingVpg.setAdapter(viewPagerAdp);

        /*
         * ========== clipChildren+pageTransformer ============*/
        //缓存个数
        clipChildrenVpg.setOffscreenPageLimit(3);
        //设置显示效果 缩放 透明度
        clipChildrenVpg.setPageTransformer(false, new OutPageTransformerClipCd());
        clipChildrenVpg.setAdapter(viewPagerAdp);

        /*
         * ========== 无限轮播 ============*/
        getClipChildrenData();
        ClipPaddingAdp viewPagerAdpTwo = new ClipPaddingAdp(this, listPlayer);
        playerVpg.setAdapter(viewPagerAdpTwo);
        playerVpg.setCurrentItem(position);
        playerVpg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //验证当前的滑动是否结束
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (position == 0) {
                        //切换，不要动画效果
                        playerVpg.setCurrentItem(listPlayer.size() - 2, false);
                    } else if (position == listPlayer.size() - 1) {
                        //切换，不要动画效果
                        playerVpg.setCurrentItem(1, false);
                    }
                }
            }
        });

        playerVpg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacks(runnable);
                        break;
                    //当你触摸时停止自动滑动
                    case MotionEvent.ACTION_UP:
                        handler.postDelayed(runnable,2000);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    //轮播线程
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isAutoPlay) {
                position = playerVpg.getCurrentItem();
                position++;
                playerVpg.setCurrentItem(position);
                handler.postDelayed(this, 2000);
            } else {
                handler.postDelayed(this, 2000);
            }

        }
    };

    private void getClipChildrenData() {
        /*
         * 无限轮播效果实现：列如有四张图片需要轮播，那么为了实现无限轮播效果我们在第一张图前面添加第四张图；在最后一张后面添加原来的第一张图
         * */
        listPlayer = new ArrayList<>();
        //新添加的第四张图
        listPlayer.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955484412&di=4015797ae24b365fb68f778a82b3ac15&imgtype=0&src=http%3A%2F%2Fpic22.nipic.com%2F20120725%2F9676681_001949824394_2.jpg");
        //正常得四张图片
        listPlayer.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955335245&di=e9661fe30faf0e5b6ee0827df2dd73c9&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121112%2F9252150_150552938000_2.jpg");
        listPlayer.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955390188&di=033a30aa04278adc5208fe2d7c21ae6a&imgtype=0&src=http%3A%2F%2Fpic16.nipic.com%2F20111006%2F6239936_092702973000_2.jpg");
        listPlayer.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955417834&di=720f6e63144231ff1a80bc6f85e25987&imgtype=0&src=http%3A%2F%2Fpic51.nipic.com%2Ffile%2F20141025%2F8649940_220505558734_2.jpg");
        listPlayer.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955484412&di=4015797ae24b365fb68f778a82b3ac15&imgtype=0&src=http%3A%2F%2Fpic22.nipic.com%2F20120725%2F9676681_001949824394_2.jpg");
        //新添加得第一张图
        listPlayer.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955335245&di=e9661fe30faf0e5b6ee0827df2dd73c9&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121112%2F9252150_150552938000_2.jpg");
    }

    private void getClipPaddingData() {
        listClipPadding = new ArrayList<>();
        listClipPadding.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955335245&di=e9661fe30faf0e5b6ee0827df2dd73c9&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121112%2F9252150_150552938000_2.jpg");
        listClipPadding.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955390188&di=033a30aa04278adc5208fe2d7c21ae6a&imgtype=0&src=http%3A%2F%2Fpic16.nipic.com%2F20111006%2F6239936_092702973000_2.jpg");
        listClipPadding.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955417834&di=720f6e63144231ff1a80bc6f85e25987&imgtype=0&src=http%3A%2F%2Fpic51.nipic.com%2Ffile%2F20141025%2F8649940_220505558734_2.jpg");
        listClipPadding.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955484412&di=4015797ae24b365fb68f778a82b3ac15&imgtype=0&src=http%3A%2F%2Fpic22.nipic.com%2F20120725%2F9676681_001949824394_2.jpg");
        listClipPadding.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955335245&di=e9661fe30faf0e5b6ee0827df2dd73c9&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121112%2F9252150_150552938000_2.jpg");
        listClipPadding.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955390188&di=033a30aa04278adc5208fe2d7c21ae6a&imgtype=0&src=http%3A%2F%2Fpic16.nipic.com%2F20111006%2F6239936_092702973000_2.jpg");
        listClipPadding.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955417834&di=720f6e63144231ff1a80bc6f85e25987&imgtype=0&src=http%3A%2F%2Fpic51.nipic.com%2Ffile%2F20141025%2F8649940_220505558734_2.jpg");
        listClipPadding.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570955484412&di=4015797ae24b365fb68f778a82b3ac15&imgtype=0&src=http%3A%2F%2Fpic22.nipic.com%2F20120725%2F9676681_001949824394_2.jpg");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pager_adp;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //每次重新进入时开始轮播
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onClick(View view) {
        handler.removeCallbacks(runnable);
    }
}
