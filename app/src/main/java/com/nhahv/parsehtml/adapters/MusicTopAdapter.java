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
import com.nhahv.parsehtml.realm.MusicTop;

import java.util.List;

/**
 * Created by Nhahv on 7/28/2016.
 * <></>
 */
public class MusicTopAdapter extends ArrayAdapter<MusicTop> {
    private Context mContext;
    private List<MusicTop> mListMusicTop;

    public MusicTopAdapter(Context context, int resource, List<MusicTop> objects) {
        super(context, resource, objects);
        mContext = context;
        mListMusicTop = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.item_music_top, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MusicTop item = mListMusicTop.get(position);
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

            if (item.getNumber() != null) {
                viewHolder.textNumber.setText(item.getNumber());
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

        private TextView textName, textArtist, textNumber;
        private LinearLayout linearLayout;

        public ViewHolder(View view) {
            textName = (TextView) view.findViewById(R.id.text_name);
            textArtist = (TextView) view.findViewById(R.id.text_artist);
            textNumber = (TextView) view.findViewById(R.id.text_number);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
        }
    }
}
