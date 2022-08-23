package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.adapter.TransactionAdapter;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.TransactionService;
import vn.aptech.warehousemobile.entity.GoodData;
import vn.aptech.warehousemobile.entity.Transaction;

public class MovementActivity extends AppCompatActivity {
    private RecyclerView rvMovement;
    private TransactionAdapter adapter;
    private TransactionService transactionService;
    private SearchView searchView;
    private Spinner spFilter;
    private String type;
    private String[] transType = new String[]{"Allocated","UnAllocated","Unqualified","Recycle","Disposed"};
    private List<Transaction> list = new ArrayList<>();
    private List<Transaction> listcpy = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);
        initUi();
        fillTransact();
        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = spFilter.getSelectedItem().toString();
                switch (spFilter.getSelectedItem().toString()){
                    case "Allocated":{
                        type="in";
                        break;
                    }
                    case "UnAllocated":{
                        type="out";
                        break;
                    }
                    case "Unqualified":{
                        type="unqualified";
                        break;
                    }
                    case "Recycle":{
                        type="Recycle";
                        break;
                    }
                    case "Disposed":{
                        type="Disposed";
                        break;
                    }
                }
                filter(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterByGoodsName(s);
                return true;

            }
        });

    }

    private void initUi() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new TransactionAdapter(list, this);
        rvMovement = findViewById(R.id.rvMovement);
        rvMovement.setAdapter(adapter);
        rvMovement.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvMovement.addItemDecoration(itemDecoration);
        spFilter = findViewById(R.id.spFilter);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, transType);
        spFilter.setAdapter(adapter);
        searchView = findViewById(R.id.searchGoodName);
    }

    private void filterByGoodsName(String newText) {
        list.clear();
        listcpy.clear();
        transactionService = ApiUtil.getTransactionService();
        transactionService.findByName(newText).enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                List<Transaction> results = response.body();
                list.addAll(results);
                listcpy.addAll(results);
                list.clear();
                if(type.isEmpty()){
                    list.addAll(listcpy);
                } else{
                    for(Transaction item: listcpy){
                        if(item.getType().equals(type)){
                            list.add(item);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {

            }
        });

    }

    private void filter(String type) {
        list.clear();
        if(type.isEmpty()){
            list.addAll(listcpy);
        } else{
            for(Transaction item: listcpy){
                if(item.getType().equals(type)){
                    list.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void fillTransact() {
        transactionService = ApiUtil.getTransactionService();
        transactionService.findByName("").enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                List<Transaction> results = response.body();
                list.addAll(results);
                listcpy.addAll(results);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {

            }
        });
    }
}