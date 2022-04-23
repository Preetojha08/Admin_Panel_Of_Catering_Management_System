package com.creatures.mycateringadminpanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerViewHolder>{

    private Context my_context;
    private List<Model_Class> users_List;

    public RecyclerviewAdapter(Context my_context, List<Model_Class> users_List) {
        this.my_context = my_context;
        this.users_List = users_List;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(my_context);
        View view = inflater.inflate(R.layout.usersdata_layout, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        Model_Class model_class = users_List.get(position);

        holder.text_view_username.setText(model_class.getUsername());
        holder.text_view_email_add.setText(model_class.getEmail());

        holder.usersdata_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int user_id = model_class.getId();
                Toast.makeText(my_context, ""+user_id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return users_List.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        TextView text_view_username,text_view_email_add;
        CardView usersdata_card_view;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            text_view_username=(TextView)itemView.findViewById(R.id.text_view_users_data_username);
            text_view_email_add=(TextView) itemView.findViewById(R.id.text_view_users_data_user_email);
            usersdata_card_view=(CardView) itemView.findViewById(R.id.main_cardview_usersdata);

        }
    }

}
