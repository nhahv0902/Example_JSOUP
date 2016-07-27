package com.nhahv.parsehtml.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhahv.parsehtml.R;
import com.nhahv.parsehtml.activities.MusicActivity;
import com.nhahv.parsehtml.adapters.MusicAdapter;
import com.nhahv.parsehtml.app.MyApplication;
import com.nhahv.parsehtml.realm.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment implements AdapterView.OnItemClickListener {

    private MusicAdapter mAdapter;
    private ListView mListView;

    private List<Music> mListMusic = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_music, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mListMusic.addAll(MyApplication.mListMusic);
        mListView = (ListView) view.findViewById(R.id.list_view);
        mAdapter = new MusicAdapter(getActivity(), R.layout.item_music, mListMusic);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), MusicActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MyApplication.MUSIC_ACTIVITY, mListMusic.get(i));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
