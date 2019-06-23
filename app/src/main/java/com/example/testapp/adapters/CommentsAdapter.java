package com.example.testapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.models.Comment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCreatedAt)
        TextView mTextViewCreatedAt;

        @BindView(R.id.tvCommentUser)
        TextView mTextViewUser;

        @BindView(R.id.tvTextComment)
        TextView mTextViewTextComment;

        @BindView(R.id.rbRating)
        RatingBar mRatingBar;



        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private ArrayList<Comment> comments;


    public CommentsAdapter(ArrayList<Comment> comments){
        this.comments = comments;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        CommentViewHolder viewHolder = new CommentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        String inputDateTime = comments.get(position).getCreatedAt();
        String convertedDateTime = Utils.convertDateTime(inputDateTime);

        holder.mRatingBar.setRating(comments.get(position).getRate());
        holder.mTextViewCreatedAt.setText(convertedDateTime);
        holder.mTextViewTextComment.setText(comments.get(position).getText());
        holder.mTextViewUser.setText(comments.get(position).getCreatedBy().getUsername());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
