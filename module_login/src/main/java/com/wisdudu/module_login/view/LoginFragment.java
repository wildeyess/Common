package com.wisdudu.module_login.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wisdudu.lib_common.base.ToolbarActivity;
import com.wisdudu.lib_common.base.ToolbarFragment;
import com.wisdudu.module_login.R;
import com.wisdudu.module_login.databinding.LoginFragmentLoginBinding;
import com.wisdudu.module_login.viewmodel.LoginViewModel;

/**
 * 文件描述：登录页
 * <p>
 * 作者：   Created by sven on 2017/10/22.
 */
@Route(path = "/login/LoginFragment")
public class LoginFragment extends ToolbarFragment {

    private LoginFragmentLoginBinding mBinding;

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment_login, container, false);
        mViewModel = new LoginViewModel(this, mBinding);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public Builder configToolbar() {
        return new ToolbarFragment
                .Builder()
                .title("登录")
                .leftButtonClickListener(new ToolbarActivity.Builder.LeftButtonClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().finish();
                    }
                });
    }

    @Override
    public boolean onBackPressedSupport() {
        getActivity().finish();
        return super.onBackPressedSupport();
    }

}
