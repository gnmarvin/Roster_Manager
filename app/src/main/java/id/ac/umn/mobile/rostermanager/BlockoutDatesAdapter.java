package id.ac.umn.mobile.rostermanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BlockoutDatesAdapter  extends RecyclerView.Adapter<BlockoutDatesAdapter.BlockDatesViewHolder>{
    private Context context;
    private List<BlockoutDatesModel> blockoutDatesModelList;

    public BlockoutDatesAdapter(List<BlockoutDatesModel> blockoutDatesModelList, Context context){
        this.blockoutDatesModelList = blockoutDatesModelList;
        this.context = context;
    }

    @Override
    public BlockDatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_blockout_dates,
                parent,false);
        BlockDatesViewHolder vh = new BlockDatesViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(BlockDatesViewHolder holder, int position) {
        final BlockoutDatesModel blockoutdate = blockoutDatesModelList.get(position);
        holder.dateText.setText(blockoutdate.getDates());
        holder.reasonText.setText(blockoutdate.getReason());
        holder.linearBlockoutDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodetailplanevent = new Intent(context, BlockoutDatesEditActivity.class);
                context.startActivity(gotodetailplanevent);
                Toast.makeText(context, blockoutdate.getDates() + " blocked because " + blockoutdate.getReason() ,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return blockoutDatesModelList.size();
    }

    public class BlockDatesViewHolder extends RecyclerView.ViewHolder {
        TextView dateText, reasonText;
        LinearLayout linearBlockoutDates;
        public BlockDatesViewHolder(View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.txt_date_blockout_date);
            reasonText = itemView.findViewById(R.id.txt_reason_blockouot_date);
            linearBlockoutDates = itemView.findViewById(R.id.linear_blockout_dates);
        }
    }
}
