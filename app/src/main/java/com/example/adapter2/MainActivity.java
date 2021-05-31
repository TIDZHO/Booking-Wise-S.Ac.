package com.example.adapter2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.adapter2.Timer.kol;

public class MainActivity extends AppCompatActivity {
    private List<ResModel> result;
    private ResAdapter adapter;
    private DatabaseReference reference;
    private Intent intent;
    private Context parent;

    public static String res;
    public static Calendar calendar1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        timer.getTimer();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("res");
//        reference.setValue(qq);
        result = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        lin.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lin);

        adapter = new ResAdapter(result, this, new ResAdapter.OnResClickListener() {
            @Override
            public void onResClick(ResModel res) {
                Toast.makeText(getApplicationContext(), "Был выбран ресторан " + res.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        updateList();

        ArrayList<String> info = new ArrayList<String>();
        info.add(res);
        info.add(String.valueOf(timer));
        info.add(String.valueOf(calendar1));
        info.add(String.valueOf(kol));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void createResult() {
        for (int i = 0; i < 2; i++) {
            result.add(new ResModel("name", "des", "key", "da"));
        }
    }

    private void updateList() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ResModel model = snapshot.getValue(ResModel.class);
                model.setKey(snapshot.getKey());
                result.add(snapshot.getValue(ResModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ResModel model = snapshot.getValue(ResModel.class);
                model.setKey(snapshot.getKey());
                int index = getItemIndex(model);
                result.set(index, model);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                ResModel model = snapshot.getValue(ResModel.class);
                model.setKey(snapshot.getKey());
                int index = getItemIndex(model);
                result.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private int getItemIndex(ResModel model) {
        int index = -1;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).key.equals(model.key))
                index = i;
            break;
        }
        return index;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.res_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}