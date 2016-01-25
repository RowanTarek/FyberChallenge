package com.ardev.assessment.fyberchallenge.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ardev.assessment.fyberchallenge.BEHandler.Offer;
import com.ardev.assessment.fyberchallenge.R;
import com.ardev.assessment.fyberchallenge.view.ItemHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by RowanTarek on 28/10/2015.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemHolder>{
    ArrayList<Offer> itemsList;
    public ItemsAdapter(ArrayList<Offer> itemsList) {
        this.itemsList =  itemsList;
    }

    @Override
    public ItemHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_preview, parent, false);

        return new ItemHolder(itemView);
    }//end onCreateViewHolder

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        final Offer curItem = itemsList.get(position);
        holder.itemTitle.setText(curItem.getTitle());
        holder.teaserView.setText(curItem.getTeaser());
        holder.payoutView.setText(curItem.getPayout());

        if(curItem.getThumbnailHiResUrl() != null && !curItem.getThumbnailHiResUrl().equals("")){
            Picasso.with(holder.itemImg.getContext()).load(curItem.getThumbnailHiResUrl()).into(holder.itemImg);

        }//end if --? img url available
    }//end onBindViewHolder

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}//end FeedItemAdapter
