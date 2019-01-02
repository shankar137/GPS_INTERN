package com.alliance.gps_intern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerAdapter mRecyclerAdapter;
    List<Upload> mUploadList;
    FirebaseFirestore mStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        mRecyclerView=findViewById(R.id.list_img);
        mStore=FirebaseFirestore.getInstance();
        mUploadList=new ArrayList<>();
        mRecyclerAdapter=new RecyclerAdapter(getApplicationContext(),mUploadList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setHasFixedSize(true);
        mStore.collection("Images").addSnapshotListener(this,new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for (DocumentChange doc:documentSnapshots.getDocumentChanges()){
                    if (doc.getType()==DocumentChange.Type.ADDED)
                    {
                        String user_id=doc.getDocument().getId();
                        Upload upload=doc.getDocument().toObject(Upload.class);
                        mUploadList.add(upload);
                        mRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


    }
}
