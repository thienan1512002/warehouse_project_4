package vn.aptech.warehousemobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.aptech.warehousemobile.R;
import vn.aptech.warehousemobile.entity.AllocateRequest;


public class AllocateAdapter extends RecyclerView.Adapter<AllocateAdapter.AllocateHolder> {
    private List<AllocateRequest> data;
    public AllocateAdapter(List<AllocateRequest> data){
        this.data = data;
    }
    @NonNull
    @Override
    public AllocateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.allocate_item,parent,false);
        return new AllocateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllocateHolder holder, int position) {
        holder.dataBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AllocateHolder extends RecyclerView.ViewHolder{

        private TextView tvId, tvName, tvLoc;
        public AllocateHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvGoodsName);
            tvLoc = itemView.findViewById(R.id.tvLoc);
        }

        public void dataBind(AllocateRequest alo){
            tvId.setText(Integer.toString(alo.getAlc_id()));
            tvName.setText(alo.getGoods_masters().getGood_data().getGoods_name());
            tvLoc.setText(Integer.toString(alo.getAlc_moved_qty()));
        }
    }
}
