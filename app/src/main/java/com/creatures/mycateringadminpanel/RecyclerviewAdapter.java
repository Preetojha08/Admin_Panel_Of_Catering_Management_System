package com.creatures.mycateringadminpanel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerViewHolder>{

    private Context my_context;
    private List<Model_Class> users_List;

    int layout_number;

    public RecyclerviewAdapter(Context my_context, List<Model_Class> users_List,int layout_number) {
        this.my_context = my_context;
        this.users_List = users_List;
        this.layout_number = layout_number;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(my_context);
        View view;
        if (layout_number ==10)
        {
            view = inflater.inflate(R.layout.usersdata_layout, null);
        }
        else if (layout_number==20)
        {
            view = inflater.inflate(R.layout.login_users_data_layout, null);
        }
        else
        {
            view = inflater.inflate(R.layout.usersdata_layout, null);
        }


        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        if(layout_number==10)
        {
            Model_Class model_class = users_List.get(position);

            holder.text_view_username.setText(model_class.getUsername());
            holder.text_view_email_add.setText(model_class.getEmail());

            holder.usersdata_card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int user_id = model_class.getId();
                    String user_email = model_class.getEmail();
                    Intent i = new Intent(my_context,UserDetailsActivity.class);
                    i.putExtra("User_id",user_id);
                    i.putExtra("User_email",user_email);
                    holder.itemView.getContext().startActivity(i);
                }
            });
        }

        if (layout_number == 20)
        {
            Model_Class model_class = users_List.get(position);

            holder.text_view_login_username.setText(model_class.getEmail());
            holder.text_view_login_date.setText(model_class.getLogin_date());

            holder.login_user_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int user_id = model_class.getId();
                    String user_email = model_class.getUsername();

                    Intent i = new Intent(my_context,UserDetailsActivity.class);
                    i.putExtra("User_id",user_id);
                    i.putExtra("User_email",user_email);
                    holder.itemView.getContext().startActivity(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return users_List.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        TextView text_view_username,text_view_email_add;
        CardView usersdata_card_view;

        TextView text_view_login_username,text_view_login_date;
        CardView login_user_cardview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            if (layout_number==10)
            {
                text_view_username=(TextView)itemView.findViewById(R.id.text_view_users_data_username);
                text_view_email_add=(TextView) itemView.findViewById(R.id.text_view_users_data_user_email);
                usersdata_card_view=(CardView) itemView.findViewById(R.id.main_cardview_usersdata);
            }
            if (layout_number==20)
            {
                text_view_login_username=(TextView) itemView.findViewById(R.id.text_view_login_users_data_username);
                text_view_login_date=(TextView) itemView.findViewById(R.id.text_view_login_users_data_and_time);
                login_user_cardview=(CardView) itemView.findViewById(R.id.main_cardview_login_usersdata);
            }

        }
    }

}
