package com.epam.mykhailo_hrois.task8.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.mykhailo_hrois.task8.R;
import com.epam.mykhailo_hrois.task8.entities.Article;
import com.epam.mykhailo_hrois.task8.entities.Articles;
import com.squareup.picasso.Picasso;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {
    private final Context context;
    private Articles articles;
    private ClickListener clickListener;

    public ArticlesAdapter(Context context, Articles articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article, viewGroup, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder articleViewHolder, int i) {
        articleViewHolder.bind(getArticles().getArticles().get(i));
    }

    @Override
    public int getItemCount() {
        return articles != null ? getArticles().getArticles().size() : 0;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;

        ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }

        void bind(Article article) {
            textView.setText(article.getTitle());
            Picasso.with(context)
                    .load(article.getUrlToImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);
            imageView.setVisibility(article.getUrlToImage() != null ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
