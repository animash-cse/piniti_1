package bd.piniti.service.AddUserInformations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import bd.piniti.service.R;

public class AddNameActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
   // private TextView nextButton, previousButton;

    // Declare Database for data fields
    private DatabaseReference databaseUser;

    // Declare Storage for images
    private StorageReference mStorage;

    //  Declare firebase user for get user id
    private FirebaseUser currentFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_name);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        /*nextButton = findViewById(R.id.next1);
        previousButton = findViewById(R.id.previous1);*/

        // Here get user id in currentFirebaseUser
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // Set database location
        databaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(currentFirebaseUser.getUid());

        /*// Button action for next
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadUserName();
            }
        });
        // Button action for previous
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
    }

    public void onClick(View view) {
        if(view.getId() == R.id.previous1) {
            onBackPressed();
        }
        if(view.getId() == R.id.next1) {
            uploadUserName();
        }
    }

    private void uploadUserName() {

        final String nameF =  firstName.getText().toString().trim();
        final String nameL = lastName.getText().toString().trim();

        if(!TextUtils.isEmpty(nameF) && !TextUtils.isEmpty(nameL) )
        {
            databaseUser.child("first_name").setValue(nameF);
            databaseUser.child("last_name").setValue(nameL);

            Toast.makeText(this, "Name Added", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddNameActivity.this, AddBirthdayActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(AddNameActivity.this, "Please fill up all the fields", Toast.LENGTH_SHORT).show();
        }
    }


}