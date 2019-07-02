package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a3130project.model.Medication;
import com.example.a3130project.viewholder.MedicationViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.example.a3130project.MainActivity.logg;

public class All_Meds extends AppCompatActivity {

    private FirebaseFirestore database;
    private RecyclerView      recyclerView;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__meds);

        recyclerView = findViewById(R.id.medicationList);
        database = FirebaseFirestore.getInstance();

        adapter = setUpMedicationAdapter(database);
        setUpRecyclerView(recyclerView, adapter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        getDelegate().onStart();
    }

    // Connect the recycler view to the medication view holder & the FireStore adapter
    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    // Creates a Firestore adapter to populate a Recycler view.
    private FirestoreRecyclerAdapter setUpMedicationAdapter(FirebaseFirestore db)
    {
        // TODO: Unlock the 'limit'
        Query query = db.collection("medications").orderBy("name").limit(5);
        FirestoreRecyclerOptions<Medication> options
                = new FirestoreRecyclerOptions.Builder<Medication>().setQuery(query, Medication.class)
                .build();

        FirestoreRecyclerAdapter adapter
                = new FirestoreRecyclerAdapter<Medication, MedicationViewHolder>(options)
        {
            // Connect each medication item in the database to the view
            @Override
            public void onBindViewHolder(MedicationViewHolder holder,
                                         int position,
                                         final Medication model)
            {
                holder.name.setText(model.name);
                holder.info.setText(model.info);

                holder.buttonDetails.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
						Intent intent = new Intent(All_Meds.this, MedicationDetails.class);
						intent.putExtra("medication", model);
						startActivity(intent);
                    }
                });

                holder.buttonAddPrescription.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(All_Meds.this, PrescriptionDetails.class);
                        intent.putExtra("medication", model);
                        intent.putExtra("actionType", "add");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public MedicationViewHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.medication_entry, group, false);
                logg("MedicationViewHolder()", "GROUP: " + group);
                return new MedicationViewHolder(view);
            }
        };
        return adapter;
    }
}
