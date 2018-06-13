package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MySchedulesAdapter extends RecyclerView.Adapter<MySchedulesAdapter.MySchedulesViewHolder> {
    private Context context;
    private List<MySchedulesModel> mySchedulesModelList;

    public MySchedulesAdapter(Context context, List<MySchedulesModel> mySchedulesModelList) {
        this.context = context;
        this.mySchedulesModelList = mySchedulesModelList;
    }

    @Override
    public MySchedulesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_my_schedule,parent,false);
        MySchedulesViewHolder vh = new MySchedulesViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MySchedulesViewHolder holder, int position) {
        final MySchedulesModel myschedules = mySchedulesModelList.get(position);
        holder.textViewEventName.setText(myschedules.getName_event_my_schedule());
        holder.textViewDate.setText(myschedules.getDate_my_schedule());
        holder.textViewTime.setText(myschedules.getTime_my_schedule());
        holder.textViewPosition.setText(myschedules.getPosition_my_schedule());
        holder.textViewCod.setText(myschedules.getCod_my_schedule());
        holder.imageButtonMoreAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //munculin more action
                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.my_schedule_options, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_cancel_replace:
                                Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
        holder.linearLayoutMySchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //accept dialog, kalo uda accept gabisa accept lagi
                Toast.makeText(context,myschedules.getName_event_my_schedule(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mySchedulesModelList.size();
    }

    public class MySchedulesViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewEventName, textViewDate, textViewTime, textViewPosition, textViewCod;
        public LinearLayout linearLayoutMySchedules;
        public ImageButton imageButtonMoreAction;

        public MySchedulesViewHolder(View itemView) {
            super(itemView);
            textViewEventName = itemView.findViewById(R.id.txt_name_event_my_schedule);
            textViewDate = itemView.findViewById(R.id.txt_date_my_schedule);
            textViewTime = itemView.findViewById(R.id.txt_position_my_schedule);
            textViewPosition = itemView.findViewById(R.id.txt_time_my_schedule);
            textViewCod = itemView.findViewById(R.id.txt_cod_my_schedule);
            linearLayoutMySchedules = itemView.findViewById(R.id.linear_my_schedule);
            imageButtonMoreAction = itemView.findViewById(R.id.more_action_my_schedule);
        }
    }
}
