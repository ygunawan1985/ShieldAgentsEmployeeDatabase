package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.shieldagentsemployeedatabase.datasource.local.database.ShieldAgentsDatabaseClient;
import com.example.shieldagentsemployeedatabase.entity.Department;
import com.example.shieldagentsemployeedatabase.entity.Employee;

import java.util.List;

public class ViewDepartments extends AppCompatActivity {

    RecyclerView rvDepartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_departments);

        rvDepartments = findViewById(R.id.rvDepartments);
        rvDepartments.setLayoutManager(new LinearLayoutManager(this));

        getDepartments();
    }

    private void getDepartments(){
        class GetDepartments extends AsyncTask<Void, Void, List<Department>>{

            @Override
            protected List<Department> doInBackground(Void... voids) {
                List<Department> departmentList = ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).
                        getShieldAgentsDatabase().departmentDao().getDepartments();

                return departmentList;
            }

            @Override
            protected void onPostExecute(List<Department> departments) {
                super.onPostExecute(departments);
                DepartmentListAdapter departmentListAdapter = new DepartmentListAdapter(departments);
                rvDepartments.setAdapter(departmentListAdapter);
            }
        }

        GetDepartments getDepartments = new GetDepartments();
        getDepartments.execute();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHome:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
        }
    }
}
