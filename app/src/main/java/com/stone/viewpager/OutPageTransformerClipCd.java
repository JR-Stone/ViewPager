package com.stone.viewpager;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import static android.support.constraint.Constraints.TAG;

/**
 * @author nnr
 */
public class OutPageTransformerClipCd implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {

        //这个就比较正常了，position == 0的时候就是图片位于中间的时候，关于图片的缩放比例各位可以按照自己的尺寸自己设计这个很简单。这里要说一下透明度的计算
        // 这个部分有的网上很多都是和缩放比例在一起计算，结果也是没有问题的，方法如下：
        // float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
        //page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        //可能是我有什么没有考虑进去，是在想不到是怎么算出来的，如有大神了解还请指教，感激不尽。
        //我这例计算的就比较简单了，就是带绝对值的一元一次方程，很简单，暂时也没发现问题。
        if (position < -1 || position > 1) {
            page.setAlpha(MIN_ALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else {
            if (position < 0) {
                float scaleX = 1 + 0.1f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - 0.1f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            }
            page.setAlpha((MIN_ALPHA-1)*Math.abs(position)+1);
        }
    }
}
