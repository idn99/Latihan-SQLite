package com.idn99.project.testsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout layoutAddData;
    RecyclerView rvData;
    EditText edtKode, edtJudul, edtPengarang;
    Button btnAdd;

    ArrayList<Buku> bukuArrayList;
    Buku buku;
    DBHelper dbHelper;
    TextView tvEmpty;

    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasi();

        fa = this;

        dbHelper = new DBHelper(getApplicationContext());
        bukuArrayList = dbHelper.getAllRecord();

        if (bukuArrayList.size() == 0){
            tvEmpty.setVisibility(View.VISIBLE);
        }else{
            tvEmpty.setVisibility(View.GONE);
            rvData.destroyDrawingCache();
            rvData.setVisibility(View.INVISIBLE);
            rvData.setVisibility(View.VISIBLE);
            BookAdapter bookAdapter = new BookAdapter(getApplicationContext(),bukuArrayList);
            rvData.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rvData.setAdapter(bookAdapter);
            bookAdapter.notifyDataSetChanged();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtKode.getText().toString().isEmpty()){
                    edtKode.setError("Tidak Boleh Kosong");
                }else if (edtJudul.getText().toString().isEmpty()){
                    edtJudul.setError("Tidak Boleh Kosong");
                }else if (edtPengarang.getText().toString().isEmpty()){
                    edtPengarang.setError("Tidak Boleh Kosong");
                }else{
                    buku = new Buku(
                            Integer.parseInt(edtKode.getText().toString()),
                            edtJudul.getText().toString(),
                            edtPengarang.getText().toString()
                            );
                    dbHelper.addRecord(buku);
                    Toast.makeText(MainActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                    layoutAddData.setVisibility(View.GONE);
                    rvData.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_data :
                tvEmpty.setVisibility(View.GONE);
                layoutAddData.setVisibility(View.VISIBLE);
                rvData.setVisibility(View.GONE);
                return true;
            case R.id.list_data:
                tvEmpty.setVisibility(View.GONE);
                layoutAddData.setVisibility(View.GONE);
                rvData.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void inisialisasi(){
        layoutAddData = findViewById(R.id.layout_add);
        rvData =  findViewById(R.id.rv_buku);
        edtKode = findViewById(R.id.edt_kode_buku);
        edtJudul = findViewById(R.id.edt_judul_buku);
        edtPengarang = findViewById(R.id.edt_pengarang);
        btnAdd = findViewById(R.id.btn_save);
        tvEmpty = findViewById(R.id.tv_empty);
    }
}
