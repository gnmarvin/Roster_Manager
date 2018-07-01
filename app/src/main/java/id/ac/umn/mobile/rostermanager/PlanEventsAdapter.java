package id.ac.umn.mobile.rostermanager;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(PlanEventsViewHolder holder, final int position) {
        final PlanEventsModel planevent = planEventsModelList.get(position);
        holder.textViewPlanEventNameEvent.setText(planevent.getName_event_plan_event());
        holder.textViewPlanEventDate.setText(planevent.getDate_plan_event());
        holder.textViewPlanEventTimeStart.setText(planevent.getTime_start_plan_event());
        holder.textViewPlanEventTimeEnd.setText(planevent.getTime_end_plan_event());
        holder.textViewPlanEventCod.setText(planevent.getCod_plan_event());
        holder.textViewPlanEventPhotoTeam.setText(planevent.getPhoto_team_plan_event());
        holder.textViewPlanEventPhotoRespond.setText(planevent.getPhoto_respond_plan_event());
        holder.textViewPlanEventCampersTeam.setText(planevent.getCampers_team_plan_event());
        holder.textViewPlanEventCampersRespond.setText(planevent.getCampers_respond_plan_event());
        //click the card
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailplanevent = new Intent(mCtx, PlanEventDetailsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("EVENT_NAME", planevent.getName_event_plan_event());
                extras.putString("EVENT_START_DATE", planevent.getDate_plan_event());
                extras.putString("EVENT_START_TIME", planevent.getTime_start_plan_event());
                extras.putString("EVENT_END_TIME", planevent.getTime_end_plan_event());
                extras.putString("EVENT_COD", planevent.getCod_plan_event());
                gotodetailplanevent.putExtras(extras);
                mCtx.startActivity(gotodetailplanevent);
            }
        });
        //click delete button
        holder.deleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventRoster eventRoster = new EventRoster();
                DeleteEvents deleteEvents = new DeleteEvents();
                eventRoster.withId(planevent.getEvent_id_plant_event());
                Toast.makeText(mCtx, new Gson().toJson(deleteEvents.withEventRoster(eventRoster)), Toast.LENGTH_SHORT).show();
                APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
                retrofit2.Call<JsonElement> deleteEventRoster = webServiceAPI.DeleteEventRoster(deleteEvents.withEventRoster(eventRoster));

                deleteEventRoster.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        JsonElement element = response.body();
                        JsonObject obj = element.getAsJsonObject();
                        JsonObject error = obj.get("error").getAsJsonObject();
                        String error_code = error.get("error_code").getAsString();
                        if(error_code.equals("0")){
                            Toast.makeText(mCtx, planevent.getName_event_plan_event()+" deleted", Toast.LENGTH_SHORT).show();
                            planEventsModelList.remove(position);
                            notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(mCtx, "fail to delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return planEventsModelList.size();
    }

    public class PlanEventsViewHolder extends RecyclerView.ViewHolder{
        CardView cardMain;
        LinearLayout linearLayout;
        TextView textViewPlanEventNameEvent, textViewPlanEventDate, textViewPlanEventTimeStart, textViewPlanEventTimeEnd, textViewPlanEventCod,
        textViewPlanEventPhotoTeam, textViewPlanEventPhotoRespond, textViewPlanEventCampersTeam, textViewPlanEventCampersRespond;
        Button deleteEvent, lockEvent, unlockEvent;

        public PlanEventsViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_plan_event);
            cardMain = (CardView) itemView.findViewById(R.id.card_item_event);
            deleteEvent = itemView.findViewById(R.id.delete_plan_event);
            lockEvent = itemView.findViewById(R.id.lock_plan_event);
            unlockEvent = itemView.findViewById(R.id.unlock_plan_event);
            textViewPlanEventNameEvent = itemView.findViewById(R.id.txt_name_event_plan_event);
            textViewPlanEventDate = itemView.findViewById(R.id.txt_date_plan_event);
            textViewPlanEventTimeStart = itemView.findViewById(R.id.txt_time_start_plan_event);
            textViewPlanEventTimeEnd = itemView.findViewById(R.id.txt_time_end_plan_event);
            textViewPlanEventCod = itemView.findViewById(R.id.txt_cod_plan_event);
            textViewPlanEventPhotoTeam = itemView.findViewById(R.id.txt_photo_team_plan_event);
            textViewPlanEventPhotoRespond = itemView.findViewById(R.id.txt_photo_respond_plan_event);
            textViewPlanEventCampersTeam = itemView.findViewById(R.id.txt_campers_team_plan_event);
            textViewPlanEventCampersRespond = itemView.findViewById(R.id.txt_campers_respond_plan_event);
        }
    }
}
