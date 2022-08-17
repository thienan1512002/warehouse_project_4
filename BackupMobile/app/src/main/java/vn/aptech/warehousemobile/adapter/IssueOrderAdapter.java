package vn.aptech.warehousemobile.adapter;

import static vn.aptech.warehousemobile.api.ApiUtil.IMG_URL;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.aptech.warehousemobile.IssueOrderDetailActivity;
import vn.aptech.warehousemobile.R;
import vn.aptech.warehousemobile.entity.IssueOrder;

public class IssueOrderAdapter extends RecyclerView.Adapter<IssueOrderAdapter.IssueOrderHolder> {
    private List<IssueOrder> data;

    private Context mContext;

    public IssueOrderAdapter(List<IssueOrder> data , Context context){
        this.data= data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public IssueOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.issue_order_item,parent,false);
        return new IssueOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueOrderHolder holder, int position) {
        final IssueOrder issueOrder = data.get(position);

        holder.dataBind(issueOrder);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(issueOrder);
            }
        });
    }

    private void onClickGoToDetail(IssueOrder issueOrder) {
        Intent it = new Intent(mContext, IssueOrderDetailActivity.class);
        it.putExtra("issue_id",issueOrder.getId());
        mContext.startActivity(it);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class IssueOrderHolder extends RecyclerView.ViewHolder{

        private RelativeLayout layoutItem;
        private TextView tvIssueGoodsName , tvIssueLocation , tvIssueQty;
        private ImageView issue_ava;
        private String good_url =IMG_URL;
        public IssueOrderHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.issue_item);
            tvIssueGoodsName = itemView.findViewById(R.id.tvIssueGoodsName);
            tvIssueLocation = itemView.findViewById(R.id.tvIssueLocation);
            tvIssueQty = itemView.findViewById(R.id.tvIssueQty);
            issue_ava = itemView.findViewById(R.id.issue_ava);
        }

        public void dataBind(IssueOrder issueOrder){
            Glide.with(mContext).load(good_url+issueOrder.getGoods_master().getGood_data().getGoods_no()+"/"+issueOrder.getGoods_master().getGood_data().getImage()).into(issue_ava);
            tvIssueQty.setText(Integer.toString(issueOrder.getQuantity()));
            tvIssueLocation.setText(issueOrder.getLocation());
            tvIssueGoodsName.setText(issueOrder.getGoods_name());
        }
    }
}
