package com.example.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<DataBinding extends ViewDataBinding> extends AppCompatActivity {

    DataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        onViewBound(binding);
    }

    protected abstract int getLayoutRes();

    protected void onViewBound(DataBinding binding) {

    }
}
