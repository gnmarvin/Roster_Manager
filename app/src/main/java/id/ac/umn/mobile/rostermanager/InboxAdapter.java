package id.ac.umn.mobile.rostermanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {
    private Context context;
    private List<InboxModel> inboxModelList;
    String response;

    public InboxAdapter(Context context, List<InboxModel> inboxModelList) {
        this.context = context;
        this.inboxModelList = inboxModelList;
    }

    @Override
    public InboxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_inbox,parent,false);
        InboxViewHolder vh = new InboxViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(InboxViewHolder holder, final int position) {
        final InboxModel inbox = inboxModelList.get(position);
        holder.textTitle.setText(inbox.getTitle());
        holder.textMessage.setText(inbox.getMessage());
        holder.linearInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Do you want to accept this job?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedData sharedData = SharedData.getInstance();
                                response = "2";
                                EventRosterCrew eventRosterCrew = new EventRosterCrew();
                                SendResponse sendResponse = new SendResponse();
                                eventRosterCrew.setId(sharedData.getRef_id(position));
                                eventRosterCrew.setCrewId(sharedData.getContact_id());
                                eventRosterCrew.setResponse(response);
                                APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
                                retrofit2.Call<JsonElement> sendResponseAPI = webServiceAPI.SendResponse(sharedData.getToken_id(), sendResponse.withEventRosterCrew(eventRosterCrew));
                                sendResponseAPI.enqueue(new Callback<JsonElement>() {
                                    @Override
                                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                        JsonElement element = response.body();
                                        JsonObject obj = element.getAsJsonObject();
                                        JsonObject error = obj.get("error").getAsJsonObject();
                                        String error_code = error.get("error_code").getAsString();
                                        if (error_code.equals("0")) {
                                            inboxModelList.remove(position);
                                        } else {
                                            String error_message = error.get("error_message").getAsString();
                                            Toast.makeText(context,error_message, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<JsonElement> call, Throwable t) {
                                    }
                                });
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedData sharedData = SharedData.getInstance();
                                response = "3";
                                EventRosterCrew eventRosterCrew = new EventRosterCrew();
                                SendResponse sendResponse = new SendResponse();
                                eventRosterCrew.setId(sharedData.getRef_id(position));
                                eventRosterCrew.setCrewId(sharedData.getContact_id());
                                eventRosterCrew.setResponse(response);
                                APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
                                retrofit2.Call<JsonElement> sendResponseAPI = webServiceAPI.SendResponse(sharedData.getToken_id(), sendResponse.withEventRosterCrew(eventRosterCrew));
                                sendResponseAPI.enqueue(new Callback<JsonElement>() {
                                    @Override
                                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                        JsonElement element = response.body();
                                        JsonObject obj = element.getAsJsonObject();
                                        JsonObject error = obj.get("error").getAsJsonObject();
                                        String error_code = error.get("error_code").getAsString();
                                        if (error_code.equals("0")) {
                                            inboxModelList.remove(position);
                                        } else {
                                            String error_message = error.get("error_message").getAsString();
                                            Toast.makeText(context,error_message, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<JsonElement> call, Throwable t) {

                                    }
                                });
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return inboxModelList.size();
    }

    public class InboxViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle, textMessage;
        LinearLayout linearInbox;

        public InboxViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title_inbox);
            textMessage = itemView.findViewById(R.id.message_inbox);
            linearInbox = itemView.findViewById(R.id.linear_inbox);
        }
    }
}
