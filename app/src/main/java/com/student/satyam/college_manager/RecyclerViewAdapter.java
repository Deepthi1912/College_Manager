package com.student.satyam.college_manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

/**
 * Created by AndroidJSon.com on 6/18/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ImageUploadInfo imageUploadInfo;
    Context context;
    List<ImageUploadInfo> MainImageUploadInfoList;
    RecyclerView recyclerView;

    public RecyclerViewAdapter(Context context, List<ImageUploadInfo> TempList,RecyclerView recyclerView) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;

        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);

        holder.imageNameTextView.setText(UploadInfo.getImagename());

        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageView;
        public TextView imageNameTextView;
        public ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);

            imageButton = (ImageButton) itemView.findViewById(R.id.imageButton2);

            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                removeAt(getAdapterPosition());
        }


    }

    public void removeAt(int position) {

        imageUploadInfo = MainImageUploadInfoList.get(position);

        FirebaseDatabase.getInstance().getReference("Admin Images").child(imageUploadInfo.getkey()).removeValue().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.removeViewAt(position);
        FirebaseStorage.getInstance().getReference("Admin Images").child(imageUploadInfo.getkey()).delete();
        MainImageUploadInfoList.remove(position);
        recyclerView.invalidate();

    }

}
