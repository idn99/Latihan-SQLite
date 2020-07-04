package com.idn99.project.testsqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    Context context;
    ArrayList<Buku> bukus;
    BookAdapter adapter;

    public BookAdapter(Context context, ArrayList<Buku> bukus) {
        this.context = context;
        this.bukus = bukus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rootView = layoutInflater.inflate(R.layout.list_design,parent, false);
        return new ViewHolder(rootView );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvJudul.setText(bukus.get(position).getKode_buku()+" | "+bukus.get(position).getNama_buku());
        holder.tvPengarang.setText(bukus.get(position).getPengarang());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper;
                dbHelper = new DBHelper(context);
                Buku buku = new Buku(
                        bukus.get(position).getKode_buku(),
                        bukus.get(position).getNama_buku(),
                        bukus.get(position).getPengarang()
                );
                dbHelper.deleteModel(buku);
                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //((MainActivity)context).finish();
                context.startActivity(intent);
                MainActivity.fa.finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return bukus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvPengarang;
        Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btn_hapus);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvPengarang = itemView.findViewById(R.id.tv_pengarang);
        }
    }
}
