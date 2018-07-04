package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ListCrewDetailsAdapter extends RecyclerView.Adapter<ListCrewDetailsAdapter.ViewHolder>{

    private List<ListCrewDetailsModel> listCrewDetailsModels;
    private Context context;

    public ListCrewDetailsAdapter(Context context, List<ListCrewDetailsModel> listCrewDetailsModels) {
        this.context = context;
        this.listCrewDetailsModels = listCrewDetailsModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_crew_plan_crews, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ListCrewDetailsModel listCrewDetails = listCrewDetailsModels.get(position); // give specific position

        holder.textViewCrewName.setText(listCrewDetails.getName_crew());
        holder.textViewPosition.setText(listCrewDetails.getPosition());
        holder.textViewTeam.setText(listCrewDetails.getTeam());
        holder.textViewRespond.setText(listCrewDetails.getRespond());
        holder.linearLayoutlistcrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoinsidelistcrew = new Intent(context, InsidePlanCrewsDetailsActivity.class);
                gotoinsidelistcrew.putExtra("position", "campers"); //parameter untuk filtering dia fotografer atau campers
                context.startActivity(gotoinsidelistcrew);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCrewDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout linearLayoutlistcrew;
        public TextView textViewCrewName;
        public TextView textViewPosition;
        public TextView textViewTeam;
        public TextView textViewRespond;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayoutlistcrew = (LinearLayout) itemView.findViewById(R.id.linear_list_crew_plan_crew);
            textViewCrewName = (TextView) itemView.findViewById(R.id.txt_crew_name_list_crew_plan_crew);
            textViewPosition = (TextView) itemView.findViewById(R.id.txt_position_list_crew_plan_crew);
            textViewRespond = (TextView) itemView.findViewById(R.id.txt_respond_list_crew_plan_crew);
            textViewTeam = (TextView) itemView.findViewById(R.id.txt_team_list_crew_plan_crew);
        }
    }
}
