package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class ListCrewDetailsAdapter extends RecyclerView.Adapter<ListCrewDetailsAdapter.ViewHolder>{

    private List<ListCrewDetailsModel> listCrewDetailsModels;
    private Context context;

    public ListCrewDetailsAdapter(List<ListCrewDetailsModel> listCrewDetailsModels, PlanCrewsDetailsActivity planCrewsDetailsActivity) {
        this.listCrewDetailsModels = listCrewDetailsModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_crew_plan_crews, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ListCrewDetailsModel listCrewDetailsModel = listCrewDetailsModels.get(position); // give specific position
        holder.editTextPosition.setText(listCrewDetailsModel.getPosition());
        //holder.editTextCrewName.setText(listCrewDetailsModel.getName_crew());
        holder.editTextCrewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Toast.makeText(context, position+" Name",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCrewDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public EditText editTextPosition;
        public EditText editTextCrewName;
        public ViewHolder(View itemView) {
            super(itemView);

            editTextPosition = (EditText) itemView.findViewById(R.id.list_position);
            editTextCrewName = (EditText) itemView.findViewById(R.id.crew_name);
        }
    }
}
