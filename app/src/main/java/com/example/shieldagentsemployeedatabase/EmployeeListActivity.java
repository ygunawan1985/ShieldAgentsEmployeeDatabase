package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.shieldagentsemployeedatabase.datasource.local.database.ShieldAgentsDatabaseClient;
import com.example.shieldagentsemployeedatabase.entity.Employee;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    RecyclerView rvEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        rvEmployees = findViewById(R.id.rvEmployees);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));

        getEmployees();
    }

    private void getEmployees() {

        class GetEmployees extends AsyncTask<Void, Void, List<Employee>>{

            @Override
            protected List<Employee> doInBackground(Void... voids) {
                List<Employee> employeeList = ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).
                        getShieldAgentsDatabase().employeeDao().getEmployees();
                return employeeList;
            }

            @Override
            protected void onPostExecute(List<Employee> employees) {
                super.onPostExecute(employees);
                EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(employees);
                rvEmployees.setAdapter(employeeListAdapter);
            }
        }

        GetEmployees getEmployees = new GetEmployees();
        getEmployees.execute();
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
