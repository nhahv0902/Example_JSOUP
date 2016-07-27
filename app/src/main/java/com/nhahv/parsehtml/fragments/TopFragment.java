package com.nhahv.parsehtml.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nhahv.parsehtml.R;
import com.nhahv.parsehtml.adapters.MusicTopAdapter;
import com.nhahv.parsehtml.app.MyApplication;
import com.nhahv.parsehtml.realm.MusicTop;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    private static final String TAG = "TopFragment";

    private MusicTopAdapter mAdapter;
    private ListView mListView;
    private static TopFragment mInstances;

    private int type = MyApplication.TOP_VN;
    private List<MusicTop> mListTop = new ArrayList<>();

    public static TopFragment getInstances(int type) {
        TopFragment fragment = new TopFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MyApplication.KEY_TOP, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        type = getArguments().getInt(MyApplication.KEY_TOP);
        Log.d(TAG, type + "");
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        switch (type) {
            case MyApplication.TOP_VN:
                mListTop.addAll(MyApplication.mListTopVN);
                break;
            case MyApplication.TOP_US:
                mListTop.addAll(MyApplication.mListTopUS);
                break;
            case MyApplication.TOP_POP:
                mListTop.addAll(MyApplication.mListTopPop);
                break;
            default:
                mListTop.addAll(MyApplication.mListTopVN);
                break;
        }
        mListView = (ListView) view.findViewById(R.id.list_view);
        mAdapter = new MusicTopAdapter(getActivity(), R.layout.item_music, mListTop);
        mListView.setAdapter(mAdapter);
    }

    public void setListView(int type) {
        switch (type) {
            case MyApplication.TOP_VN:
                mListTop.addAll(MyApplication.mListTopVN);
                break;
            case MyApplication.TOP_US:
                mListTop.addAll(MyApplication.mListTopUS);
                break;
            case MyApplication.TOP_POP:
                mListTop.addAll(MyApplication.mListTopPop);
                break;
            default:
                mListTop.addAll(MyApplication.mListTopVN);
                break;
        }
        mAdapter.notifyDataSetChanged();
    }
}
