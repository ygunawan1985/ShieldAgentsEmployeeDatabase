package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.shieldagentsemployeedatabase.datasource.local.database.ShieldAgentsDatabaseClient;
import com.example.shieldagentsemployeedatabase.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class FilterEmployeeActivity extends AppCompatActivity {

    Spinner spinnerDepartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_employee);

        spinnerDepartments = findViewById(R.id.spinnerDepartments);

        loadSpinners();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFilter:
                String departmentSelected = String.valueOf(spinnerDepartments.getSelectedItem());
                Intent filterIntent = new Intent(this, EmployeeListActivity.class);
                filterIntent.putExtra("filter", departmentSelected);
                startActivity(filterIntent);
                break;
            case R.id.btnAddNewEmployee:
                Intent addNewEmployeeIntent = new Intent(this, NewEmployeeActivity.class);
                startActivity(addNewEmployeeIntent);
                break;
            case R.id.btnViewEmployees:
                Intent viewEmployeeListIntent = new Intent(this, EmployeeListActivity.class);
                startActivity(viewEmployeeListIntent);
                break;
        }
    }

    private void loadSpinners() {
        class LoadSpinners extends AsyncTask<Void, Void, List<Department>> {

            @Override
            protected List<Department> doInBackground(Void... voids) {
                List<Department> departmentList = ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).
                        getShieldAgentsDatabase().departmentDao().getDepartments();

                return departmentList;
            }

            @Override
            protected void onPostExecute(List<Department> departments) {
                super.onPostExecute(departments);
                List<String> strList = new ArrayList<>();

                if(!departments.isEmpty()){
                    for(Department department : departments){
                        strList.add(department.getName());
                    }

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, strList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDepartments.setAdapter(dataAdapter);
                }

            }
        }

        LoadSpinners loadSpinners = new LoadSpinners();
        loadSpinners.execute();
    }
}
