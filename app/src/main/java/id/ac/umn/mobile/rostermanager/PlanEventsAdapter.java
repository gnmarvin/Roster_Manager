package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlanEventsAdapter extends RecyclerView.Adapter<PlanEventsAdapter.PlanEventsViewHolder>{

    private Context mCtx;
    private List<PlanEventsModel> planEventsModelList;

    public PlanEventsAdapter(Context mCtx, List<PlanEventsModel> planEventsModelList) {
        this.mCtx = mCtx;
        this.planEventsModelList = planEventsModelList;
    }

    @Override
    public PlanEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mCtx)
                .inflate(R.layout.card_plan_events, parent, false);
        // set the view's size, margins, paddings and layout parameters
        PlanEventsViewHolder vh = new PlanEventsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlanEventsViewHolder holder, int position) {
        PlanEventsModel planevent = planEventsModelList.get(position);
        holder.textViewPlanEventNameEvent.setText(planevent.getName_event_plan_event());
        holder.textViewPlanEventDate.setText(planevent.getDate_plan_event());
        holder.textViewPlanEventTime.setText(planevent.getTime_plan_event());
        holder.textViewPlanEventCod.setText(planevent.getCod_plan_event());
        holder.textViewPlanEventPhotoTeam.setText(planevent.getPhoto_team_plan_event());
        holder.textViewPlanEventPhotoRespond.setText(planevent.getPhoto_respond_plan_event());
        holder.textViewPlanEventCampersTeam.setText(planevent.getCampers_team_plan_event());
        holder.textViewPlanEventCampersRespond.setText(planevent.getCampers_respond_plan_event());
    }

    @Override
    public int getItemCount() {
        return planEventsModelList.size();
    }

    public class PlanEventsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlanEventNameEvent, textViewPlanEventDate, textViewPlanEventTime, textViewPlanEventCod,
        textViewPlanEventPhotoTeam, textViewPlanEventPhotoRespond, textViewPlanEventCampersTeam, textViewPlanEventCampersRespond;

        public PlanEventsViewHolder(View itemView) {
            super(itemView);

            textViewPlanEventNameEvent = itemView.findViewById(R.id.txt_name_event_plan_event);
            textViewPlanEventDate = itemView.findViewById(R.id.txt_date_plan_event);
            textViewPlanEventTime = itemView.findViewById(R.id.txt_time_plan_event);
            textViewPlanEventCod = itemView.findViewById(R.id.txt_cod_plan_event);
            textViewPlanEventPhotoTeam = itemView.findViewById(R.id.txt_photo_team_plan_event);
            textViewPlanEventPhotoRespond = itemView.findViewById(R.id.txt_photo_respond_plan_event);
            textViewPlanEventCampersTeam = itemView.findViewById(R.id.txt_campers_team_plan_event);
            textViewPlanEventCampersRespond = itemView.findViewById(R.id.txt_campers_respond_plan_event);
        }
    }
}
