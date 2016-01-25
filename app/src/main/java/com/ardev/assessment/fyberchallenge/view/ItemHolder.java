package com.ardev.assessment.fyberchallenge.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardev.assessment.fyberchallenge.R;


/**
 * Created by RowanTarek on 28/10/2015.
 */
public class ItemHolder extends RecyclerView.ViewHolder{
    public  ImageView itemImg;
    public TextView itemTitle, teaserView, payoutView;
    private final View itemView;

    public ItemHolder(View itemView) {
        super(itemView);
        itemImg = (ImageView) itemView.findViewById(R.id.offerPreviewImg);
        itemTitle = (TextView) itemView.findViewById(R.id.offerTitleView);
        teaserView = (TextView) itemView.findViewById(R.id.offerTeaserView);
        payoutView = (TextView) itemView.findViewById(R.id.offerAmountView);
        this.itemView = itemView;
    }//end holder constructor

    public void setClickListener(View.OnClickListener viewClickListener){
        itemView.setOnClickListener(viewClickListener);
    }
}//end FeedItemHolder
