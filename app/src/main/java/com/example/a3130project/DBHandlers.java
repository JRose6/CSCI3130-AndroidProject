package com.example.a3130project;

import android.util.Log;

import com.example.a3130project.model.Prescription;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DBHandlers {
    public static void prescriptionInsertUpdate(Prescription prescription){
        String              profileId         = FirebaseAuth.getInstance().getUid();
        String              prescriptionsPath = "profiles/" + profileId + "/prescriptions";
        CollectionReference prescriptionsRef  = FirebaseFirestore.getInstance()
                .collection("profiles").document(profileId)
                .collection("prescriptions");
        DocumentReference docRef;
        if ( prescription.getId() == null || prescription.getId().equals("null") )
        {
            docRef = prescriptionsRef.document();
            prescription.setId(docRef.getId());
        }
        else
        {
            docRef = prescriptionsRef.document(prescription.getId());
        }


        docRef.set(prescription).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                Log.w("updateDatabaseEntry().onSuccessListener().onSuccess()",
                        "It Worked!");
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(Exception e)
            {
                Log.w("updateDatabaseEntry().onFailureListener().onFailure()",
                        "Error updating document...", e);
            }
        });
    }
}