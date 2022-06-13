package com.example.crud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.TampilData>{
    private List<ModelAgent> mList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterData(List mList, Activity activity) {
        this.mList = mList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterData.TampilData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);

        return new TampilData(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.TampilData holder, int position) {
        final  ModelAgent data = mList.get(position);
        holder.tampil_agent.setText("Nama Agent : " + data.getAgent());
        holder.tampil_skiils.setText("Skill Agent : " + data.getSkiils());

        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int iya) {
                        database.child("Agent").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Berhasil di hapus", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Gagal di hapus", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          dialog.dismiss();
                    }
                }).setMessage("Apakah yakin mau menghapus " + data.getAgent());
                builder.show();
            }
        });


        holder.card_hasil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialog = new DialogForm(data.getAgent(), data.getSkiils(), data.getKey(), "Ubah");
                dialog.show(manager, "form");
              return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public  class TampilData extends RecyclerView.ViewHolder {
        TextView tampil_agent, tampil_skiils;
        ImageView btn_hapus;
        CardView card_hasil;

        public TampilData(@NonNull View itemView) {
            super(itemView);

            tampil_agent = itemView.findViewById(R.id.tampil_agent);
            tampil_skiils = itemView.findViewById(R.id.tampil_skills);
            card_hasil = itemView.findViewById(R.id.card_hasil);
            btn_hapus = itemView.findViewById(R.id.hapus);

        }
    }

}
