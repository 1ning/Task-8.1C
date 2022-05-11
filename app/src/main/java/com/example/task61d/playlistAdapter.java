package com.example.task61d;

import static android.content.Intent.createChooser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class playlistAdapter extends RecyclerView.Adapter<playlistAdapter.MyViewHolder>{
    private List<String>list;
    private View inflater;
    private Context mContext;



    public playlistAdapter(Context context, List<String> list) {
        this.list = list;
        mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext).inflate(R.layout.add_contacts2,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.context.setText(list.get(position));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView context;
        public MyViewHolder(View itemView) {
            super(itemView);
            context = (TextView) itemView.findViewById(R.id.context);
        }
    }
}

