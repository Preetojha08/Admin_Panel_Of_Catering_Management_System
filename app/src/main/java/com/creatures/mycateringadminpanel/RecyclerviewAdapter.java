package com.creatures.mycateringadminpanel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
        else if (layout_number==30)
        {
            view = inflater.inflate(R.layout.event_cards, null);
        }
        else if (layout_number==40)
        {
            view = inflater.inflate(R.layout.thali_deatils, null);
        }
        else if (layout_number==50)
        {
            view = inflater.inflate(R.layout.inquiry_layout, null);
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

        if (layout_number == 30)
        {
            Model_Class model_class = users_List.get(position);

            holder.event_db_title_tv.setText(model_class.getEvent_name());
            int event_id = model_class.getEvent_id();
            String img_link = model_class.getEvent_img_link();

            Log.e("Recycler View Data"," ID: "+event_id+"link: "+img_link);

            try
            {
                Picasso.get().load(img_link).into(holder.event_db_img_view);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.e("Recycler View Data part 2"," Error aaya hai");
            }
        }

        if (layout_number == 40)
        {
            Model_Class model_class = users_List.get(position);

            holder.thali_db_title_tv.setText(model_class.getUsername());
            holder.thali_db_dec_tv.setText(model_class.getEmail());

            int event_id = model_class.getId();
            String img_link = model_class.getLogin_date();

            Log.e("Recycler View Thali Data"," ID: "+event_id+"link: "+img_link);

            try
            {
                Picasso.get().load(img_link).into(holder.thali_db_img_view);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.e("Recycler View Data part 2"," Error aaya hai");
            }
        }

        if(layout_number==50)
        {
            Model_Class model_class = users_List.get(position);

            holder.inquiry_username_tv.setText(model_class.getUsername());
            String inq_1 = model_class.getInq_1();
            String inq_2 = model_class.getInq_2();

            String up_inq_1 = inq_1.replace("null,","");
            String up_inq_2 = inq_2.replace("null,","");

            up_inq_1 = up_inq_1.replace("null.","");
            up_inq_2 = up_inq_2.replace("null.","");

            String final_inq = up_inq_1.trim()+" and "+up_inq_2.trim();
            holder.inquiry_desc_tv.setText(final_inq);
            int id = model_class.getUserid();
            String email = model_class.getEmail();
            int inquiry_layout=100;

            holder.inquiry_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(my_context,InquiryUserDetailsActivity.class);
                    i.putExtra("User_id",id);
                    i.putExtra("User_email",email);
                    i.putExtra("inquiry_layout",inquiry_layout);
                    i.putExtra("User_inquiry",final_inq);
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

        MaterialCardView event_db_cardview;
        ImageView event_db_img_view;
        TextView event_db_title_tv;

        ImageView thali_db_img_view;
        TextView thali_db_title_tv,thali_db_dec_tv;

        TextView inquiry_username_tv,inquiry_desc_tv;
        CardView inquiry_cardview;

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
            if (layout_number==30)
            {
                event_db_cardview=(MaterialCardView) itemView.findViewById(R.id.event_db_card_display);
                event_db_img_view=(ImageView) itemView.findViewById(R.id.event_db_image_view);
                event_db_title_tv=(TextView) itemView.findViewById(R.id.event_db_title_text_view);
            }
            if (layout_number==40)
            {
                thali_db_title_tv=(TextView) itemView.findViewById(R.id.thali_details_title_textview);
                thali_db_dec_tv=(TextView) itemView.findViewById(R.id.thali_details_dec_textview);
                thali_db_img_view=(ImageView) itemView.findViewById(R.id.thali_details_imageview);
            }
            if (layout_number==50)
            {
                inquiry_username_tv=(TextView) itemView.findViewById(R.id.text_view_inquiry_users_data_username);
                inquiry_desc_tv=(TextView) itemView.findViewById(R.id.text_view_inquiry_users_inquiry);
                inquiry_cardview=(CardView) itemView.findViewById(R.id.main_cardview_inquiry_usersdata);
            }

        }
    }

}
