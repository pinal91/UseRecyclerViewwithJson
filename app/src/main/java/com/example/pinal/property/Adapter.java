package com.example.pinal.property;

/**
 * Created by pinal on 16/5/16.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pinal on 2/22/2016.
 */
public class Adapter extends BaseAdapter {

    ImageLoader imageLoader;
    DisplayImageOptions options;

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<HashMap<String,String>> data;

    HashMap<String,String> result = new HashMap<String,String>();

    public Adapter(Context context, ArrayList<HashMap<String,String>> list) {

        this.context = context;
        this.data = list;


        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc().cacheInMemory()
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);

        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory()
                .cacheOnDisc().resetViewBeforeLoading()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .build();
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
        ImageView iv1,iv2;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder")
        View view = layoutInflater.inflate(R.layout.simple_list_disp,parent,false);


        tv1=(TextView) view.findViewById(R.id.tv1);
        tv2=(TextView) view.findViewById(R.id.tv2);
        tv3=(TextView)view.findViewById(R.id.tv3);
        tv4=(TextView)view.findViewById(R.id.tv4);
        tv5=(TextView)view.findViewById(R.id.tv5);
        tv6=(TextView)view.findViewById(R.id.tv6);
        tv7=(TextView)view.findViewById(R.id.tv7);

        iv1=(ImageView)view.findViewById(R.id.iv1);
        iv2=(ImageView)view.findViewById(R.id.iv2);


        result = data.get(position);

        tv1.setText(result.get("name"));
        tv2.setText(result.get("area"));
        tv3.setText(result.get("type"));
        tv4.setText(result.get("bed"));

        tv5.setText(result.get("bath"));
        tv6.setText(result.get("rooms"));
        tv7.setText(result.get("country"));

        imageLoader.displayImage(result.get("image"),iv1, options);
        imageLoader.displayImage(result.get("logo"),iv2, options);

        return view;
    }
}
