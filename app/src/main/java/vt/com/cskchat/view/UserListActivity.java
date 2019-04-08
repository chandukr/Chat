package vt.com.cskchat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vt.com.cskchat.MainActivity;
import vt.com.cskchat.R;
import vt.com.cskchat.adapter.ClickListenerChatFirebase;
import vt.com.cskchat.adapter.UserListAdapter;
import vt.com.cskchat.model.UserModel;

public class UserListActivity extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabaseReference;
    RecyclerView mRecyclerView;
    ArrayList<UserModel> modelList = new ArrayList<>();
    UserModel mUserModel;
    String mCurrUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mRecyclerView = findViewById(R.id.recyclerList);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("MyDb").child("UserDetails");
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
           mCurrUser = bundle.getString("UserName") ;
        }

        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserModel userModel = snapshot.getValue(UserModel.class);

                    Log.e("dataSnapshot", "onDataChange: " + userModel.getName());

                    modelList.add(userModel);


                }
                updateAdapter(modelList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Log.e("cc", "onCreate: " + modelList.indexOf(2));





    }

    public void updateAdapter(final ArrayList<UserModel> userData)
    {
        UserListAdapter userListAdapter = new UserListAdapter(getApplicationContext(), userData, new CustomItemClickListener() {


            @Override
            public void onItemClick(View v, int position) {

                mUserModel=userData.get(position);


            String userName =  mUserModel.getName();

                Log.e("position", "onItemClick: "+position+" "+userName);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("UserName",userName);
                intent.putExtra("mCurrUser",mCurrUser);
            startActivity(intent);

        }
        });
        mRecyclerView.setAdapter(userListAdapter);
    }
}
