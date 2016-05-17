package com.example.pinal.property;

/**
 * Created by pinal on 16/5/16.
 */
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
   /* List<DataFish> data= Collections.emptyList();
    DataFish current;*/
    int currentPos=0;

    ImageLoader imageLoader;
    DisplayImageOptions options;

    ArrayList<HashMap<String,String>> data;

    HashMap<String,String> result = new HashMap<String,String>();


    // create constructor to innitilize context and data sent from MainActivity
    public Adapter(Context context, ArrayList<HashMap<String,String>> list){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=list;

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

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.simple_list_disp, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder= (MyHolder) holder;
        result=data.get(position);

       myHolder.tv1.setText(result.get("name"));

        myHolder. tv2.setText(result.get("country"));
        myHolder.tv3.setText(result.get("type"));
        myHolder.tv4.setText(result.get("bed"));

        myHolder.tv5.setText(result.get("bath"));


        if(result.get("rooms").isEmpty()){
            myHolder.tv6.setVisibility(View.GONE);

        }
        else{
            myHolder.tv6.setVisibility(View.VISIBLE);
            myHolder.tv6.setText(result.get("rooms"));

        }


        if(result.get("area").isEmpty()){
            myHolder.tv7.setVisibility(View.GONE);

        }
        else{
            myHolder.tv7.setVisibility(View.VISIBLE);
            myHolder.tv7.setText(result.get("area"));

        }

        imageLoader.displayImage(result.get("image"),myHolder.iv1, options);
        imageLoader.displayImage(result.get("logo"),myHolder.iv2, options);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
        ImageView iv1,iv2;
        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            tv1=(TextView) itemView.findViewById(R.id.tv1);
            tv2=(TextView) itemView.findViewById(R.id.tv2);
            tv3=(TextView)itemView.findViewById(R.id.tv3);
            tv4=(TextView)itemView.findViewById(R.id.tv4);
            tv5=(TextView)itemView.findViewById(R.id.tv5);
            tv6=(TextView)itemView.findViewById(R.id.tv6);
            tv7=(TextView)itemView.findViewById(R.id.tv7);

            iv1=(ImageView)itemView.findViewById(R.id.iv1);
            iv2=(ImageView)itemView.findViewById(R.id.iv2);
        }

    }

}