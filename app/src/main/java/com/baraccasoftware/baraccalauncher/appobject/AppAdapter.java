package com.baraccasoftware.baraccalauncher.appobject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baraccasoftware.baraccalauncher.R;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by angelomoroni on 03/01/15.
 */
public class AppAdapter extends BaseAdapter {
    private ArrayList list;
    private Context context;
    private LayoutInflater inflater;

    public AppAdapter(ArrayList list, Context context) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public AppAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addAll(Collection<? extends AppContent.AppItem> collection, boolean clear){
        if(clear) clear();
        this.list.addAll(collection);
    }

    public void addAll(Collection<? extends AppContent.AppItem> collection){
        addAll(collection,false);
    }

    public void clear(){
        list = new ArrayList();
    }

    public AppContent.AppItem get(int position){
        return (AppContent.AppItem) getItem(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.app_row_layout,null,false);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon_imageView);
            holder.name = (TextView) convertView.findViewById(R.id.app_nome_textView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        AppContent.AppItem appItem = get(position);
        holder.icon.setImageDrawable(appItem.icon);
        holder.name.setText(appItem.label);
        return convertView;
    }

    class ViewHolder{
        ImageView icon;
        TextView name;
    }


}
