package com.stone.viewpager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stone.viewpager.base.DefaultBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author nnr
 */
public class ClipChildrenNavigation extends DefaultBaseActivity {

    @BindView(R.id.ic_receive)
    TextView receiver;
    @BindView(R.id.ic_order)
    TextView order;
    @BindView(R.id.ic_subscribe)
    TextView subscribe;
    @BindView(R.id.ic_mine)
    TextView mine;
    @BindView(R.id.content)
    TextView content;

    /**
     * clipChildren是ViewGroup中的一个很好的属性，用于定义子元素超出父元素的部分是否进行绘制.
     * 通常用于动画效果中需要绘制超出原有尺寸的元素时使用。clipChildren的值是哥boolean型，false
     * 表示可以超出父元素，true表示不可超出父元素。而且它的使用需要用在父元素上。
     * 并且自身也要使用layout_gravity="bottom"属性
     **/

    @Override
    protected void init(Bundle savedInstanceState) {
    }

    @OnClick({R.id.ic_receive, R.id.ic_order, R.id.centerFl, R.id.ic_subscribe, R.id.ic_mine})
    public void OnClick(View view) {
        setDefaultSelect();
        switch (view.getId()) {
            case R.id.ic_receive:
                receiver.setSelected(true);
                content.setText("首页");
                break;
            case R.id.ic_order:
                order.setSelected(true);
                content.setText("嘿嘿");
                break;
            case R.id.centerFl:
                showLongToast("点击了中间按钮");
                break;
            case R.id.ic_subscribe:
                subscribe.setSelected(true);
                content.setText("哈哈");
                break;
            case R.id.ic_mine:
                mine.setSelected(true);
                content.setText("呵呵");
                break;
            default:
                break;
        }
    }

    private void setDefaultSelect() {
        receiver.setSelected(false);
        order.setSelected(false);
        subscribe.setSelected(false);
        mine.setSelected(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clip_children_navigation;
    }

    @Override
    public void onClick(View view) {

    }
}
