package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return vh;
    }

    @Override
    public void onBindViewHolder(PlanCrewsViewHolder holder, int position) {
        PlanCrewsModel plancrew = planCrewsModelList.get(position);
        holder.textViewPlanCrewNameEvent.setText(plancrew.getName_event_plan_crew());
        holder.textViewPlanCrewDate.setText(plancrew.getDate_plan_crew());
        holder.textViewPlanCrewTime.setText(plancrew.getTime_plan_crew());
        holder.textViewPlanCrewCod.setText(plancrew.getCod_plan_crew());
        holder.textViewPlanCrewTeam.setText(plancrew.getTeam_plan_crew());
        holder.textViewPlanCrewRespond.setText(plancrew.getRespond_plan_crew());
    }

    @Override
    public int getItemCount() {
        return planCrewsModelList.size();
    }

    public class PlanCrewsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlanCrewNameEvent, textViewPlanCrewDate, textViewPlanCrewTime, textViewPlanCrewCod, textViewPlanCrewTeam, textViewPlanCrewRespond;

        public PlanCrewsViewHolder(View itemView) {
            super(itemView);

            textViewPlanCrewNameEvent = itemView.findViewById(R.id.txt_name_event_plan_crew);
            textViewPlanCrewDate = itemView.findViewById(R.id.txt_date_plan_crew);
            textViewPlanCrewTime = itemView.findViewById(R.id.txt_time_plan_crew);
            textViewPlanCrewCod = itemView.findViewById(R.id.txt_cod_plan_crew);
            textViewPlanCrewTeam = itemView.findViewById(R.id.txt_team_plan_crew);
            textViewPlanCrewRespond = itemView.findViewById(R.id.txt_respond_plan_crew);
        }
    }
}
