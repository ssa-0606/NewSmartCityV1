package com.imikasa.hospital.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.hospital.pojo.Patient;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<Patient> patients;
    private RecyclerView.ViewHolder holder;

    public PatientAdapter(int resourceId, List<Patient> patients) {
        this.resourceId = resourceId;
        this.patients = patients;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(resourceId, null);
        holder = new RecyclerView.ViewHolder(inflate) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View itemView = holder.itemView;
        Patient patient = patients.get(position);

        TextView name = itemView.findViewById(R.id.patient_card_name);
        TextView id = itemView.findViewById(R.id.patient_card_id);

        name.setText(patient.getName());
        id.setText(patient.getCardId());
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }
}
