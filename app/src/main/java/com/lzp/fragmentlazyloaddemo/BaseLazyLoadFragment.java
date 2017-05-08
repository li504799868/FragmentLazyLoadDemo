package com.lzp.fragmentlazyloaddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by li.zhipeng on 2017/5/8.
 *
 *  懒加载的Fragment
 */

public abstract class BaseLazyLoadFragment extends BaseFragment {

    /**
     * 是否已经初始化结束
     */
    private boolean isPrepare;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLazyLoad(true);
        isPrepare = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 创建时要判断是否已经显示给用户，加载数据
        onVisibleToUser();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 显示状态发生变化
        onVisibleToUser();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    /**
     * 判断是否需要加载数据
     */
    private void onVisibleToUser() {
        // 如果已经初始化完成，并且显示给用户
        if (isPrepare && getUserVisibleHint()) {
            loadData();
        }
    }

}
