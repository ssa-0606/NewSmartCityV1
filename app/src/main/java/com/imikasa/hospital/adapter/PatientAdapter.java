package com.imikasa.hospital.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.hospital.HospitalViewModel;
import com.imikasa.hospital.fragments.PatientUpdateFragment;
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
        LinearLayout linearLayout = itemView.findViewById(R.id.patient_jump_change_update);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.hospital_container, PatientUpdateFragment.class,null,"patient-update")
                        .hide(appCompatActivity.getSupportFragmentManager().findFragmentByTag("patient-show"))
                        .commit();
                HospitalViewModel hospitalViewModel = new ViewModelProvider(appCompatActivity).get(HospitalViewModel.class);
                hospitalViewModel.setPatientMutableLiveData(position);
            }
        });
        name.setText(patient.getName());
        id.setText(patient.getCardId());
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }
}
