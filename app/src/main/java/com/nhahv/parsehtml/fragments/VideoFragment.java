package com.nhahv.parsehtml.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nhahv.parsehtml.R;
import com.nhahv.parsehtml.adapters.VideoAdapter;
import com.nhahv.parsehtml.app.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    private VideoAdapter mAdapter;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mListView = (ListView) view.findViewById(R.id.list_view);
        mAdapter = new VideoAdapter(getActivity(), R.layout.item_music, MyApplication.mListVideo);
        mListView.setAdapter(mAdapter);
    }
}
