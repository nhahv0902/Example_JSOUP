package com.nhahv.parsehtml.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhahv.parsehtml.R;
import com.nhahv.parsehtml.realm.Music;

import java.util.List;

/**
 * Created by Nhahv on 7/28/2016.
 * <></>
 */
public class MusicAdapter extends ArrayAdapter<Music> {

    private Context mContext;
    private List<Music> mListMusic;
    private int mLayout;

    public MusicAdapter(Context context, int resource, List<Music> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayout = resource;
        mListMusic = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_music, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Music item = mListMusic.get(position);
        final int intPosition = position;
        if (item != null) {
            if (item.getName() != null) {
                viewHolder.textName.setText(item.getName());
            } else {
                viewHolder.textName.setVisibility(View.INVISIBLE);
            }

            if (item.getAuthor() != null) {
                viewHolder.textArtist.setText(item.getAuthor());
            } else {
                viewHolder.textArtist.setVisibility(View.INVISIBLE);
            }

            if (item.getLike() != null) {
                viewHolder.textLike.setText(item.getLike());
            } else {
                viewHolder.textLike.setVisibility(View.INVISIBLE);
            }

            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "" + intPosition, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {

        private TextView textName, textArtist, textLike;
        private LinearLayout linearLayout;

        public ViewHolder(View view) {
            textName = (TextView) view.findViewById(R.id.text_name);
            textArtist = (TextView) view.findViewById(R.id.text_artist);
            textLike = (TextView) view.findViewById(R.id.text_like);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
        }
    }
}
