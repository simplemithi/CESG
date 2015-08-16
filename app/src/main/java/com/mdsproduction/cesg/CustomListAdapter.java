package com.mdsproduction.cesg;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SimpleMithi on 2015-08-13.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> itemname;
    private final Integer[] imgid;

    public CustomListAdapter(Activity context, List<String> itemname, Integer[] imgid) {
        super(context, R.layout.custome_list, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.custome_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position));
        imageView.setImageResource(imgid[position]);
        extratxt.setText("Description " + itemname.get(position));
        return rowView;

    };
}
