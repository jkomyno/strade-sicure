package it.concorso.sanstino.stradesicure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.concorso.android.stradesicure.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkomyno on 07/09/2016.
 */

public class MyMenuAdapter extends BaseAdapter {

    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;

    public MyMenuAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("Quiz", R.color.quiz, R.drawable.ic_question_answer_white_24dp));
        mItems.add(new Item("Gioco", R.color.game, R.drawable.ic_gamepad_white_24dp));
        mItems.add(new Item("Tutorial", R.color.tutorial, R.drawable.ic_help_outline_white_24dp));
        mItems.add(new Item("Sito", R.color.site, R.drawable.ic_open_in_browser_white_24dp));
        mItems.add(new Item("Impostazioni", R.color.settings, R.drawable.ic_settings_white_24dp));
        mItems.add(new Item("Open source", R.color.opensource, R.drawable.ic_github_white_24dp));

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).backgroundColorId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        ImageView icon;
        TextView name;

        if (v==null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.menu_picture,v.findViewById(R.id.menu_picture));
            v.setTag(R.id.square_image_view_title, v.findViewById(R.id.square_image_view_title));
            v.setTag(R.id.square_image_view_icon, v.findViewById(R.id.square_image_view_icon));
        }

        picture = (ImageView) v.getTag(R.id.menu_picture);
        icon = (ImageView) v.getTag(R.id.square_image_view_icon);
        name = (TextView) v.getTag(R.id.square_image_view_title);

        Item item = getItem(i);

        icon.setImageResource(item.iconId);

        picture.setImageResource(item.backgroundColorId);
        name.setText(item.name);
        name.setTextSize(20);

        return v;
    }

    private static class Item {
        public final String name;
        public final int backgroundColorId;
        public final int iconId;

        Item(String name, int backgroundColorId, int iconId)  {
            this.name = name;
            this.backgroundColorId = backgroundColorId;
            this.iconId = iconId;
        }
    }
}
