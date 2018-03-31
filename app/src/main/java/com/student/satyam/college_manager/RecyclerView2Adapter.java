package com.student.satyam.college_manager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by AndroidJSon.com on 6/18/2017.
 */

public class RecyclerView2Adapter extends RecyclerView.Adapter<RecyclerView2Adapter.ViewHolder> {

    Context context;
    List<AdminNotice> listOfNotice;
    RecyclerView recyclerView;

    public RecyclerView2Adapter(Context context, List<AdminNotice> TempList, RecyclerView recyclerView) {

        this.listOfNotice = TempList;

        this.context = context;

        this.recyclerView = recyclerView;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview2_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AdminNotice adminNotice = listOfNotice.get(position);

        holder.subjectextView.setText(adminNotice.getSubject());

    }


    @Override
    public int getItemCount() {

        return listOfNotice.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView subjectextView;
        public ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            subjectextView = (TextView) itemView.findViewById(R.id.rItemsubject);

            imageButton = (ImageButton) itemView.findViewById(R.id.imageButton2);

            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            removeAt(getAdapterPosition());
        }

    }




    public void removeAt(int position) {

        FirebaseDatabase.getInstance().getReference("Admin Notice").child(listOfNotice.get(position).getSubject()).removeValue();
        listOfNotice.remove(position);
        notifyItemRemoved(position);
    }
}



