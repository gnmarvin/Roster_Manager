package id.ac.umn.mobile.rostermanager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InboxFragment extends Fragment {
    List<InboxModel> inboxList;
    RecyclerView rv;
    String contact_id, token_id, set_notif_body, set_ref_id;
    String[] notif_body = new String[20];
    int size = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootview = inflater.inflate(R.layout.fragment_inbox, container, false);

        rv = rootview.findViewById(R.id.recycler_inbox);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        SharedData sharedData = SharedData.getInstance();
        contact_id = sharedData.getContact_id();
        token_id = sharedData.getToken_id();

        APIService webServiceAPI = APIClient.getApiClient().create(APIService.class);
        retrofit2.Call<JsonElement> inbox = webServiceAPI.Inbox(token_id, contact_id);
        inbox.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement element = response.body();
                JsonObject obj = element.getAsJsonObject();
                JsonArray data = obj.get("notification_queue").getAsJsonArray();
                for (int i = 0; i < data.size(); i++) {
                    JsonObject singleData = data.get(i).getAsJsonObject();
                    if (singleData.get("notification_type_label").getAsString().equals("Need Response") && !singleData.get("ref_id").isJsonNull()) {
                        set_notif_body = singleData.get("notification_alt_body").getAsString();
                        set_ref_id = singleData.get("ref_id").getAsString();
                        setter(set_notif_body, set_ref_id, size);
                        size++;
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

        inboxList = new ArrayList<>();
        return rootview;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Inbox");
    }

    public void setter(String notif_body, String ref_id, Integer size) {
        SharedData sharedData = SharedData.getInstance();
        this.notif_body[size] = notif_body;
         sharedData.setRef_id(ref_id);
        inboxList.add(new InboxModel("Roster",this.notif_body[size]));

        InboxAdapter adapter = new InboxAdapter(getContext(), inboxList);
        rv.setAdapter(adapter);
    }
}
