package com.example.shara.bbcnews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by shara on 3/19/2017.
 */

public class BBCRecyclerViewAdapter extends RecyclerView.Adapter<BBCRecyclerViewAdapter.ViewHolder> {

    Context mcontext;
    List<News> mObjects;
    int currentLayout;
    INewsRecyclerView mainActivity;

    public BBCRecyclerViewAdapter(Context mcontext, List<News> mObjects, int currentLayout) {
        this.mcontext = mcontext;
        this.mObjects = mObjects;
        this.currentLayout = currentLayout;
        mainActivity = (INewsRecyclerView) mcontext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitleText;
        public ImageView newsImageLogo;
        public ImageView newsBlock;
        public RelativeLayout gridRelativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            newsTitleText = (TextView) itemView.findViewById(R.id.newsTitleTextView);
            newsImageLogo = (ImageView) itemView.findViewById(R.id.bbcNewsThumbnailView);
            newsBlock = (ImageView)  itemView.findViewById(R.id.blockImageView);
            gridRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.gridRelativeLayout);
        }
    }

    @Override
    public BBCRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View contextView = layoutInflater.inflate(currentLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(contextView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BBCRecyclerViewAdapter.ViewHolder holder, final int position) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd, MMM yyyy");
        Log.d("onBind","entered");
        final RelativeLayout gridLayout = (RelativeLayout) holder.gridRelativeLayout;
        TextView newsTitle = holder.newsTitleText;
        newsTitle.setText(mObjects.get(position).getTitle());
        ImageView newsLogo = holder.newsImageLogo;
        ImageView newsBlock = holder.newsBlock;
        String logoImageUrl = mObjects.get(position).getThumbNailImage();
        if (logoImageUrl.equals("")) {
            Picasso.with(this.mcontext)
                    .load(R.drawable.bbcnews)
                    .placeholder(R.drawable.bbcnews)
                    .error(R.drawable.bbcnews)
                    .into(newsLogo);
        } else {
            Picasso.with(this.mcontext)
                    .load(logoImageUrl)
                    .placeholder(R.drawable.bbcnews)
                    .error(R.drawable.bbcnews)
                    .into(newsLogo);
        }

        newsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.displayNews(mObjects.get(position));
            }
        });

        newsLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.displayNews(mObjects.get(position));
            }
        });

        newsBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.addToDatabase(mObjects.get(position));
                Log.d("newsBlock", mObjects.get(position).toString());
                mObjects.remove(position);
                notifyItemRemoved(position);
                Log.d("newsBlock", mObjects.get(position).toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }



    public interface INewsRecyclerView{
        public void displayNews(News news);
        public void addToDatabase(News news);
    }
}
