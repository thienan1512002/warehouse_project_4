package vn.aptech.warehousemobile.adapter;

import static vn.aptech.warehousemobile.api.ApiUtil.IMG_URL;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.aptech.warehousemobile.GoodsDetailActivity;
import vn.aptech.warehousemobile.R;
import vn.aptech.warehousemobile.entity.GoodData;

public class GoodsDataAdapter extends RecyclerView.Adapter<GoodsDataAdapter.GoodsMasterHolder> {
    private List<GoodData> data;
    private Context mContext;

    public GoodsDataAdapter(List<GoodData> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public GoodsMasterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.goods_item,parent,false);
        return new GoodsMasterHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GoodsMasterHolder holder, int position) {
        final GoodData goodsData = data.get(position);

        holder.dataBind(goodsData);

        holder.layoutGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(goodsData);
            }
        });
    }
    private void onClickGoToDetail(GoodData goodData){
        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
        intent.putExtra("goods_no",goodData.getGoods_no());
        intent.putExtra("goods_name",goodData.getGoods_name());
        mContext.startActivity(intent);
        //Toast.makeText(mContext, goodData.getGoods_name(), Toast.LENGTH_SHORT).show();

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GoodsMasterHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutGood;
        private TextView tvGoodName, tvGoodUnit,tvGoodPrice;
        private ImageView imgGood;
        private String URL =IMG_URL;
        public GoodsMasterHolder(View view) {
            super(view);
            tvGoodName = view.findViewById(R.id.tvGoodName);
            tvGoodUnit = view.findViewById(R.id.tvGoodUnit);
            tvGoodPrice = view.findViewById(R.id.tvGoodPrice);
            imgGood = view.findViewById(R.id.imvGoods);
            layoutGood = view.findViewById(R.id.layoutGood);
        }
        public void dataBind(GoodData goodData){
            tvGoodName.setText(goodData.getGoods_name());
            tvGoodUnit.setText(goodData.getGoods_package());
            tvGoodPrice.setText(goodData.getPrice()+"$");
            Glide.with(mContext).load(URL+goodData.getGoods_no()+"/"+goodData.getImage()).into(imgGood);
        }
    }
}
