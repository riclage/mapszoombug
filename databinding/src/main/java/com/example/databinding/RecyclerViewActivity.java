package com.example.databinding;

import android.support.v7.widget.RecyclerView;
import com.example.databinding.databinding.ActivityMapsBinding;

public class RecyclerViewActivity extends BaseActivity<ActivityMapsBinding> {

    protected RecyclerView recyclerView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_maps;
    }

    @Override
    protected void onViewBound(ActivityMapsBinding binding) {
        super.onViewBound(binding);
        recyclerView = binding.recyclerView;
    }
}
