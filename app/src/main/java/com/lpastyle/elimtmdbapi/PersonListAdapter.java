package com.lpastyle.elimtmdbapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder> {

    private Context mContext;
    private List<PersonData> mPersons;

    public PersonListAdapter(Context context, List<PersonData> persons) {
        this.mContext = context;
        this.mPersons = persons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        {
            PersonData curItem = mPersons.get(position);
            holder.nameTv.setText(curItem.getName());
            holder.popularityTv.setText(String.valueOf(curItem.getPopularity()));

            Picasso.with(mContext)
                    .load(ApiClient.IMAGE_BASE_URL + curItem.getProfilePath())
                    .placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.sym_def_app_icon)
                    .into(holder.photoIv);
        }
    }


    @Override
    public int getItemCount() {
        return mPersons.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photoIv;
        TextView nameTv;
        TextView popularityTv;

        public ViewHolder(View v) {
            super(v);
            photoIv = v.findViewById(R.id.photo_iv);
            nameTv = v.findViewById(R.id.name_tv);
            popularityTv = v.findViewById(R.id.popularity_tv);
        }
    }


}

