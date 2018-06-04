package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        holder.editTextCrewName.setText(listCrewDetailsModel.getName_crew());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.Position_Photo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerPosition.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return listCrewDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Spinner spinnerPosition;
        public EditText editTextCrewName;
        public ViewHolder(View itemView) {
            super(itemView);

            spinnerPosition = (Spinner) itemView.findViewById(R.id.list_position);
            editTextCrewName = (EditText) itemView.findViewById(R.id.crew_name);
        }
    }
}
