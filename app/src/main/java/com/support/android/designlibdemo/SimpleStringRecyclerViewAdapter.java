package com.support.android.designlibdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class SimpleStringRecyclerViewAdapter extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Pokemon> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;
        public int mBoundInt;

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.text);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public String getValueAt(int position) {
        return mValues.get(position).getName();
    }

    public SimpleStringRecyclerViewAdapter(Context context, List<Pokemon> items) {
        Log.d("Yash", "items "+items.toString());
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("Yash", "position "+position);
        holder.mBoundString = mValues.get(position).getName();
        holder.mBoundInt = mValues.get(position).getDrawable();
        holder.mTextView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PokemonDetailActivity.class);
                intent.putExtra(PokemonDetailActivity.EXTRA_NAME, holder.mBoundString);
                intent.putExtra(PokemonDetailActivity.EXTRA_DRAWABLE, holder.mBoundInt);
                context.startActivity(intent);
            }
        });

        Glide.with(holder.mImageView.getContext())
            .load(holder.mBoundInt)
            .fitCenter()
            .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        Log.d("Yash", "values "+mValues.size());
        return mValues.size();
    }
}
