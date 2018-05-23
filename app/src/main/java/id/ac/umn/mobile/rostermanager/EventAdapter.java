package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private Context mCtx;
    private List<Event_model> eventModelList;

    public EventAdapter(Context mCtx, List<Event_model> eventModelList) {
        this.mCtx = mCtx;
        this.eventModelList = eventModelList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mCtx)
                .inflate(R.layout.card_event, null);
        // set the view's size, margins, paddings and layout parameters
        EventViewHolder vh = new EventViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, final int position) {
        Event_model event = eventModelList.get(position);
        holder.textViewEvent.setText(event.getName_event());
        holder.textViewDate.setText(event.getDate_event());
        holder.textViewTime.setText(event.getTime_event());
        holder.textViewCod.setText(event.getCod_event());
    }

    @Override
    public int getItemCount() {
        return eventModelList.size();
    }

    class EventViewHolder extends  RecyclerView.ViewHolder{
        TextView textViewEvent, textViewDate, textViewTime, textViewCod;

        public EventViewHolder(View itemView){
            super(itemView);

            textViewEvent = itemView.findViewById(R.id.txt_nama_event);
            textViewDate = itemView.findViewById(R.id.txt_tanggal_event);
            textViewTime = itemView.findViewById(R.id.txt_time_event);
            textViewCod = itemView.findViewById(R.id.txt_cod_event);
        }
    }
}