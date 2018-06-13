package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {
    private Context context;
    private List<InboxModel> inboxModelList;

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
    public void onBindViewHolder(InboxViewHolder holder, int position) {
        final InboxModel inbox = inboxModelList.get(position);
        holder.textTitle.setText(inbox.getTitle());
        holder.textMessage.setText(inbox.getMessage());
        holder.linearInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, inbox.getTitle(), Toast.LENGTH_LONG).show();
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
