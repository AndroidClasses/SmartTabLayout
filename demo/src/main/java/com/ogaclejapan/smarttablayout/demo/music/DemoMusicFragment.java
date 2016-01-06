package com.ogaclejapan.smarttablayout.demo.music;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.demo.R;
import com.ogaclejapan.smarttablayout.demo.model.CommonItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DemoMusicFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private DemoMusicAdapter mMusicAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//    int position = FragmentPagerItem.getPosition(getArguments());
//    TextView title = (TextView) view.findViewById(R.id.item_title);
//    title.setText(String.valueOf(position));
        ButterKnife.bind(this, view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        mMusicAdapter = new DemoMusicAdapter(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMusicAdapter);
        mMusicAdapter.setOnItemClickListener(themeItemClickListener);
    }

    DemoMusicAdapter.TextStickClickListener themeItemClickListener = new DemoMusicAdapter.TextStickClickListener() {
        @Override
        public void onItemClick(String label, CommonItem commonItem) {
        }
    };


//    private static final int LOADER_ALL = 0;
    private int mPosition = 0;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 首次加载所有图片
        //new LoadImageTask().execute();
        Bundle args = getArguments();
        mPosition = FragmentPagerItem.getPosition(args);
        getActivity().getSupportLoaderManager().initLoader(mPosition, null, mLoaderCallback);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {
        private final Uri IMAGE_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        private final String[] IMAGE_PROJECTION = {
                MediaStore.Audio.Media.TITLE,  //音乐名
                MediaStore.Audio.Media.DURATION,            //音乐的总时间
                MediaStore.Audio.Media.ARTIST,          //艺术家
                MediaStore.Audio.Media._ID,             //id号
                MediaStore.Audio.Media.DISPLAY_NAME,        //音乐文件名
                MediaStore.Audio.Media.DATA
                };

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if(id == mPosition) {
                CursorLoader cursorLoader = new CursorLoader(getActivity(),
                        IMAGE_URI, IMAGE_PROJECTION,
                        null, null, IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            }

            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null) {
                List<CommonItem> images = new ArrayList<>();
                int count = data.getCount();
                if (count > 0) {
                    data.moveToFirst();
                    do {
//                        String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                        String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                        long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));

                        CommonItem image = new CommonItem(name, dateTime);
                        images.add(image);
                    } while(data.moveToNext());

                    mMusicAdapter.setData(images);
                }
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

}
