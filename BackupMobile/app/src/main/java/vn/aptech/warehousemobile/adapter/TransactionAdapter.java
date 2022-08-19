package vn.aptech.warehousemobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import vn.aptech.warehousemobile.R;
import vn.aptech.warehousemobile.entity.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder>{
    private List<Transaction> data;
    private Context mContext;

    public TransactionAdapter(List<Transaction> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transactions_item,parent,false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        final Transaction transaction = data.get(position);

        holder.dataBind(transaction);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class TransactionHolder extends RecyclerView.ViewHolder{
        private LinearLayout layoutTransact;
        private TextView tvTransNo, tvTransName, tvTransFrom, tvTransTo, tvTransQty, tvTransType;
        public TransactionHolder(View view) {
            super(view);
            tvTransFrom = view.findViewById(R.id.tvTransFrom);
            tvTransTo = view.findViewById(R.id.tvTransTo);
            tvTransNo = view.findViewById(R.id.tvTransNo);
            tvTransQty = view.findViewById(R.id.tvTransQty);
            tvTransType = view.findViewById(R.id.tvTransType);
//            tvTransName = view.findViewById(R.id.tvTransName);


            layoutTransact = view.findViewById(R.id.layoutTransac);
        }
        public void dataBind(Transaction transaction ){
            tvTransNo.setText(String.valueOf(transaction.getId()));
            tvTransQty.setText(String.valueOf(transaction.getQuantity()));
            tvTransFrom.setText(transaction.getFrom_loc());
            tvTransTo.setText(transaction.getTo_loc());
            if(transaction.getType().equals("in")){
                tvTransType.setText("Allocated");
            }else{
                tvTransType.setText("UnAllocated");
            }

//            tvTransName.setText(transaction.getGoods_name());

        }
    }
}
