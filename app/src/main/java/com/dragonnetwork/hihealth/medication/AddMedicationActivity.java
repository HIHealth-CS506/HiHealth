package com.dragonnetwork.hihealth.medication;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dragonnetwork.hihealth.R;
import com.dragonnetwork.hihealth.user.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.HashMap;
import java.util.Map;


public class AddMedicationActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private EditText medName;
    private EditText medType;
    private EditText medTotalPills;
    private EditText medStrength;
    private EditText medDosage;
    private EditText medFrequency;

    private static FirebaseFirestore db;
    private static CollectionReference userDB;
    private static DocumentReference medDoc;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        db = FirebaseFirestore.getInstance();
        userDB = db.collection("Users");
        medDoc = db.collection("Medications").document();

        medName = findViewById(R.id.medication_name_editText);
        medType = findViewById(R.id.medication_type_editText);
        medTotalPills = findViewById(R.id.total_pills_editText);
        medStrength = findViewById(R.id.medication_strength_editText);
        medDosage = findViewById(R.id.medication_dosage_editText);
        medFrequency = findViewById(R.id.medication_frequency_editText);

        // setup the on button push func
        Button btn = findViewById(R.id.add_medication_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Map<String, Object> med = new HashMap<>();
                med.put("Disease", null);
                med.put("Frequency", medFrequency.getText().toString());
                med.put("Name", medName.getText().toString());
                med.put("NumPills", Double.parseDouble(medTotalPills.getText().toString()));
                med.put("Timestamp", null);
                AlertDialog.Builder builder = new AlertDialog.Builder(AddMedicationActivity.this).
                        setTitle("Add Medication").
                        setMessage("Save your changes?").
                        setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("Medications")
                                    .add(med).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        Toast.makeText(
                                                AddMedicationActivity.this,
                                                "Added Medication.",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                        Log.d("MedUI", "DocumentSnapshot written with ID: " + task.getResult().getId());
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(
                                                AddMedicationActivity.this,
                                                "Failed to add to database.",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                    }
                                });
                                Toast.makeText(
                                        AddMedicationActivity.this,
                                        "Changes Saved.",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }).
                        setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(
                                        AddMedicationActivity.this,
                                        "Cancelled changes.",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });
                builder.show();
            }
        });
    }
}
