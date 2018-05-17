package id.ac.umn.mobile.rostermanager;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public EventViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_item_event);
            mTextView = (TextView) v.findViewById(R.id.txt_nama_event);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_event, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EventViewHolder vh = new EventViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset[position]);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = mDataset[position];
                Log.d("CardView", "CardView Clicked: " + currentValue);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}