package com.example.imagecatalog.ui.imagelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.imagecatalog.R;
import com.example.imagecatalog.base.BaseFragment;
import com.example.imagecatalog.databinding.FragmentImageListBinding;
import com.example.imagecatalog.model.dataclass.Data;
import com.example.imagecatalog.ui.imagedetail.ImageDetailFragment;
import com.example.imagecatalog.util.factory.ViewModelFactory;

import javax.inject.Inject;

public class ImageListFragment extends BaseFragment implements ImageListAdapter.OnItemClickListener {

    @Inject
    ViewModelFactory viewModelFactory;
    private ImageListViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImageListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentImageListBinding fragmentImageListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container, false);
        fragmentImageListBinding.setLifecycleOwner(this);
        fragmentImageListBinding.setViewModel(viewModel);

        initRecyclerView(fragmentImageListBinding.rvImageList);

        return fragmentImageListBinding.getRoot();
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        DiffUtil.ItemCallback<Data> dataItemCallback = new DiffUtil.ItemCallback<Data>() {
            @Override
            public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
                return oldItem.getId().equals(newItem.getId());
            }
        };
        ImageListAdapter adapter = new ImageListAdapter(dataItemCallback, this);
        viewModel.getPagedListLiveData().observe(this, adapter::submitList);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Data data) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ImageDetailFragment.DATA, data);
        ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        imageDetailFragment.setArguments(bundle);
        openScreen(imageDetailFragment);
    }

}
