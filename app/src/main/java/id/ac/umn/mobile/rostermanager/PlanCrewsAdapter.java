package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PlanCrewsAdapter extends RecyclerView.Adapter<PlanCrewsAdapter.PlanCrewsViewHolder>{

    private Context mCtx;
    private List<PlanCrewsModel> planCrewsModelList;

    public PlanCrewsAdapter(Context mCtx, List<PlanCrewsModel> planCrewsModelList) {
        this.mCtx = mCtx;
        this.planCrewsModelList = planCrewsModelList;
    }

    @Override
    public PlanCrewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mCtx)
                .inflate(R.layout.card_plan_crews, parent, false);
        // set the view's size, margins, paddings and layout parameters
        PlanCrewsViewHolder vh = new PlanCrewsViewHolder(v);
//        Toast.makeText(mCtx, getItemCount(), Toast.LENGTH_SHORT).show();
        return vh;
    }

    @Override
    public void onBindViewHolder(PlanCrewsViewHolder holder, int position) {
        final PlanCrewsModel plancrew = planCrewsModelList.get(position);
        holder.textViewPlanCrewNameEvent.setText(plancrew.getName_event_plan_crew());
        holder.textViewPlanCrewDate.setText(plancrew.getDate_plan_crew());
        holder.textViewPlanCrewTimeStart.setText(plancrew.getTime_start_plan_crew());
        holder.textViewPlanCrewTimeEnd.setText(plancrew.getTime_end_plan_crew());
        holder.textViewPlanCrewCod.setText(plancrew.getCod_plan_crew());
        holder.textViewPlanCrewTeam.setText(plancrew.getTeam_plan_crew());
        holder.textViewPlanCrewRespond.setText(plancrew.getRespond_plan_crew());
        //click the card
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedData sharedData = SharedData.getInstance();
                sharedData.setEvent_id_plan_crew(plancrew.getEvent_id());
                Intent gotodetailcrew = new Intent(mCtx, PlanCrewsDetailsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("EVENT_NAME", plancrew.getName_event_plan_crew());
                extras.putString("EVENT_START_DATE", plancrew.getDate_plan_crew());
                extras.putString("EVENT_START_TIME", plancrew.getTime_start_plan_crew());
                extras.putString("EVENT_END_TIME", plancrew.getTime_end_plan_crew());
                extras.putString("EVENT_COD", plancrew.getCod_plan_crew());
                gotodetailcrew.putExtras(extras);
                mCtx.startActivity(gotodetailcrew);
//                Toast.makeText(mCtx, plancrew.getName_event_plan_crew(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return planCrewsModelList.size();
    }

    public class PlanCrewsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlanCrewNameEvent, textViewPlanCrewDate, textViewPlanCrewTimeStart, textViewPlanCrewTimeEnd, textViewPlanCrewCod, textViewPlanCrewTeam, textViewPlanCrewRespond;
        public LinearLayout linearLayout;
        public PlanCrewsViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_plan_crew);
            textViewPlanCrewNameEvent = itemView.findViewById(R.id.txt_name_event_plan_crew);
            textViewPlanCrewDate = itemView.findViewById(R.id.txt_date_plan_crew);
            textViewPlanCrewTimeStart = itemView.findViewById(R.id.txt_time_start_plan_crew);
            textViewPlanCrewTimeEnd = itemView.findViewById(R.id.txt_time_end_plan_crew);
            textViewPlanCrewCod = itemView.findViewById(R.id.txt_cod_plan_crew);
            textViewPlanCrewTeam = itemView.findViewById(R.id.txt_team_plan_crew);
            textViewPlanCrewRespond = itemView.findViewById(R.id.txt_respond_plan_crew);
        }
    }
}
