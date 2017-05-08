package com.lzp.fragmentlazyloaddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by li.zhipeng on 2017/5/8.
 * <p>
 * Fragment 的基类
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 布局id
     */
    private View contentView;

    /**
     * 是否启用懒加载，此属性仅对BaseLazyLoadFragment有效
     * */
    private boolean isLazyLoad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(setLayoutId(), container, false);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 初始化
        init();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 如果不是懒加载模式，创建就加载数据
        if (!isLazyLoad){
            loadData();
        }
    }

    /**
     * 设置加载的布局Id
     */
    protected abstract int setLayoutId();

    /**
     * 初始化操作
     */
    protected abstract void init();

    /**
     * findViewById
     */
    protected View findViewById(int id) {
        return contentView.findViewById(id);
    }

    /**
     * 懒加载数据
     */
    protected abstract void loadData();


    /**
     * 是否启用懒加载，此方法仅对BaseLazyLoadFragment有效
     * */
    public void setLazyLoad(boolean lazyLoad) {
        isLazyLoad = lazyLoad;
    }
}
