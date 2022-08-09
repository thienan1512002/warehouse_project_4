package vn.aptech.warehousemobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.aptech.warehousemobile.AllocateDetailActivity;
import vn.aptech.warehousemobile.R;
import vn.aptech.warehousemobile.entity.AllocateRequest;


public class AllocateAdapter extends RecyclerView.Adapter<AllocateAdapter.AllocateHolder> {
    private List<AllocateRequest> data;

    private Context mContext;
    public AllocateAdapter(List<AllocateRequest> data , Context context){
        this.mContext = context;
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

        final AllocateRequest allocateRequest = data.get(position);

        holder.dataBind(allocateRequest);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onClickGoToDetail(allocateRequest);
            }
        });
    }
    private void onClickGoToDetail(AllocateRequest alo){
        Intent intent = new Intent(mContext, AllocateDetailActivity.class);
        intent.putExtra("alc_id",alo.getAlc_id());
        mContext.startActivity(intent);
        Toast.makeText(mContext, alo.getGoods_masters().getGood_data().getGoods_name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AllocateHolder extends RecyclerView.ViewHolder{

        private RelativeLayout layoutItem;
        private TextView tvId, tvName, tvLoc;
        private ImageView img_avatar;
        private final String URL ="http://10.0.0.18:8080/goods-photos/";
        public AllocateHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvGoodsName);
            tvLoc = itemView.findViewById(R.id.tvLoc);
            img_avatar = itemView.findViewById(R.id.img_avatar);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }

        public void dataBind(AllocateRequest alo){
            tvId.setText(Integer.toString(alo.getAlc_id()));
            tvName.setText(alo.getGoods_masters().getGood_data().getGoods_name());
            tvLoc.setText(Integer.toString(alo.getAlc_moved_qty()));
            Glide.with(mContext).load(URL+alo.getGoods_masters().getGood_data().getGoods_no()+"/"+alo.getGoods_masters().getGood_data().getImage()).into(img_avatar);
        }
    }
}
