package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    Context mContext;
    ArrayList<NewsItem> mNewsItems;
    private NewsListClick mClickListener;

    public ArrayList<NewsItem> getmNewsItems() {
        return mNewsItems;
    }

    public interface NewsListClick{
        void onItemClick(int i);
    }

    public void setmNewsItems(ArrayList<NewsItem> mNewsItems, NewsListClick listener) {
        this.mNewsItems = mNewsItems;
        this.mClickListener = listener;
    }

    public NewsAdapter(Context context, ArrayList<NewsItem> newsItems) {
        this.mContext = context;
        this.mNewsItems = newsItems;
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleOfArticle;
        TextView descriptionOfArticle;
        TextView timeOfArticle;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            titleOfArticle = (TextView) itemView.findViewById(R.id.tv_title);
            descriptionOfArticle = (TextView) itemView.findViewById(R.id.tv_description);
            timeOfArticle = (TextView) itemView.findViewById(R.id.tv_time);
            // COMPLETED (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
            //itemView.setOnClickListener(this);
        }

        void bind(final int listIndex) {
            titleOfArticle.setText("Title" + mNewsItems.get(listIndex).getTitle());
            descriptionOfArticle.setText(mNewsItems.get(listIndex).getDescription());
            timeOfArticle.setText(mNewsItems.get(listIndex).getPublishedAt());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            String urlString = mRepos.get(getAdapterPosition()).getUrl();
//            Intent intent = new Intent(mContext, WebActivity.class);
//            intent.putExtra("urlString", urlString);
//            mContext.startActivity(intent);
            int pos = getAdapterPosition();
            mClickListener.onItemClick(pos);
            //mContext.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.google.com")));
        }
    }
}
