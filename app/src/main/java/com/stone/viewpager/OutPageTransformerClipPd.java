package com.stone.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author nnr
 */
public class OutPageTransformerClipPd implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1 || position > 1) {
            page.setAlpha(MIN_ALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else {
            if (position < 0) {
                float scaleX = 1 + 0.1f * position;
                page.setAlpha(MIN_ALPHA + (1 + position-0.1f) * (1 - MIN_ALPHA));
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            } else {
                //这里减0.1f的原因是因为当图片滑动的时候最右侧图片的位置即position从1->0;中间图片的position 0->-1;所以在使用clipChildren的时候,显示在中间位置图片的position并等于0；
                // 但是现在咱们使用的是clipToPadding属性,这时候在中间位置图片的position并不等于0而是等于0.1
                //所以为了实现图片在中间位置时透明度为1必须要在方程中加上或减去0.1 关于方程的算法很简单一元一次方程这里就不说了。
                float scaleX = 1 - 0.1f * position;
                page.setAlpha(MIN_ALPHA + (1 - position + 0.1f) * (1 - MIN_ALPHA));
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            }
        }
    }
}
