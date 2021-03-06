package com.wisdudu.module_login.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.hawk.Hawk;
import com.wisdudu.lib_common.base.BaseFragment;
import com.wisdudu.module_login.R;
import com.wisdudu.module_login.constants.Constants;
import com.wisdudu.module_login.constants.LoginState;
import com.wisdudu.module_login.databinding.LoginFragmentSplashBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件描述：闪屏页
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */
@Route(path = "/login/SplashFragment")
public class SplashFragment extends BaseFragment {

    private LoginFragmentSplashBinding mBinding;

    public static SplashFragment newInstance() {
        Bundle args = new Bundle();
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment_splash, container, false);
        mBinding.setViewModel(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toNextPage();
    }

    private void toNextPage() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .lastElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Boolean isLogin = Hawk.get(LoginState.IS_LOGIN, false);
                        Boolean isIntoGuidePage = Hawk.get(Constants.IS_INTO_GUIDE_PAGE, false);
                        if (isLogin) {
                            ARouter.getInstance().build("/main/MainActivity").navigation();
                            getActivity().finish();
                        } else {
                            if (isIntoGuidePage) {
                                startWithPop("/login/LoginFragment");
                            } else {
                                startWithPop("/login/GuideFragment");
                            }
                        }
                    }
                });
    }
}
