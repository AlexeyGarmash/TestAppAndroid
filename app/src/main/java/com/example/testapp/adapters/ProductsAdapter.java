package com.example.testapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.common.Common;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.models.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardProduct)
        CardView cardView;

        @BindView(R.id.imageProduct)
        ImageView imageViewProduct;

        @BindView(R.id.productTitle)
        TextView textViewTitle;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private ArrayList<Product> products;
    private Context mContext;

    private final OnItemClickListener<Product> clickListener;

    public ProductsAdapter(ArrayList<Product> products, OnItemClickListener<Product> clickListener){
        this.products = products;
        this.clickListener = clickListener;
        mContext = BaseApplication.getsAppComponent().getContext();
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.textViewTitle.setText(products.get(position).getTitle());
        Utils.setImageByURL(
                holder.imageViewProduct,
                holder.imageViewProduct.getWidth(),
                holder.imageViewProduct.getHeight(),
                Common.BASE_IMAGES + products.get(position).getImgUrl());
        holder.cardView.setOnClickListener(view -> clickListener.onItemClick(products.get(position)));
    }

    @Override
    public int getItemCount() {
        return products.size();
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

    public ArrayList<Product> getProducts() {
        return products;
    }


}
