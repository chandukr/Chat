package vt.com.cskchat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vt.com.cskchat.R;
import vt.com.cskchat.model.UserModel;

public class RegisterActivity extends AppCompatActivity {
    EditText mEmail,mUserName,mPass;
    Button mRegistration,mLogin;
    private DatabaseReference mFirebaseDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail = findViewById(R.id.tvEmail2);
        mPass = findViewById(R.id.TvPass);
        mUserName = findViewById(R.id.Tvusername);
        mRegistration = findViewById(R.id.TvReg);
        mLogin = findViewById(R.id.TvLogin);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("MyDb").child("UserDetails");

        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String passw = mPass.getText().toString().trim();
                String userName = mUserName.getText().toString().trim();

                //UserData userModel  = new UserData(email,userName,passw);
                UserModel model = new UserModel(userName,email,passw);

                mFirebaseDatabaseReference.child(userName).setValue(model);

                mFirebaseDatabaseReference.child(userName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserData userModel1  = dataSnapshot.getValue(UserData.class);
                        Log.e("userModel1", "onDataChange: "+userModel1.getEmail()+" "+userModel1.getPassword() +" "+userModel1.getUserName() );
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
              startActivity(new Intent(getApplicationContext(),TestLogin2Activity.class));
           //startActivity(new Intent(getApplicationContext(),UserListActivity.class));

            }
        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),TestLogin2Activity.class));
            }
        });





    }
}
