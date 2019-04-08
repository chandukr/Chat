package vt.com.cskchat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vt.com.cskchat.MainActivity;
import vt.com.cskchat.R;
import vt.com.cskchat.model.UserModel;

public class TestLogin2Activity extends AppCompatActivity {
    EditText mUserName,mPass;
    Button mRegistration,mLogin;
    private DatabaseReference mFirebaseDatabaseReference;
    String userName,mName;

    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_login2);
       ;
        mPass = findViewById(R.id.TvPass);
        mUserName = findViewById(R.id.Tvusername);
        mRegistration = findViewById(R.id.TvReg);
        mLogin = findViewById(R.id.TvLogin);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("MyDb").child("UserDetails");
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String passw = mPass.getText().toString().trim();
                userName = mUserName.getText().toString().trim();

                mFirebaseDatabaseReference.child(userName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        userModel = dataSnapshot.getValue(UserModel.class);

                        mName =userModel.getName();
                        checkCredential();

                        Log.e("ch", "onDataChange: "+dataSnapshot.getValue() );
                        Log.e("chhh", "onDataChange: "+userModel.getName() );
                    }

                    {
                        Log.e("userModel", "onCreate: "+"hh" );
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });



        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });



    }
    public  void checkCredential()
    {
        if (mName!=null)
        {
            Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
            intent.putExtra("UserName",mName);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"worng Credentisl",Toast.LENGTH_SHORT).show();
        }
    }
}
