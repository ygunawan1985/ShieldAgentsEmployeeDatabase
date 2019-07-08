package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shieldagentsemployeedatabase.datasource.local.database.ShieldAgentsDatabaseClient;
import com.example.shieldagentsemployeedatabase.entity.Department;
import com.example.shieldagentsemployeedatabase.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class NewEmployeeActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etStreetAddress, etCity, etState, etZip, etTaxId, etPosition;
    private Spinner spinnerDepartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etStreetAddress = findViewById(R.id.etStreetAddress);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etZip = findViewById(R.id.etZip);
        etTaxId = findViewById(R.id.etTaxId);
        etPosition = findViewById(R.id.etPosition);
        spinnerDepartments = findViewById(R.id.spinnerDepartments);
        loadSpinners();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddAgent:
                addEmployee();
                break;
        }
    }

    private void addEmployee(){
        final String firstName = etFirstName.getText().toString();
        final String lastName = etLastName.getText().toString();
        final String streetAddress = etStreetAddress.getText().toString();
        final String city = etCity.getText().toString();
        final String state = etState.getText().toString();
        final String zip = etZip.getText().toString();
        final String taxId = etTaxId.getText().toString();
        final String position = etPosition.getText().toString();
        final String department = String.valueOf(spinnerDepartments.getSelectedItem());

        if(firstName.isEmpty()){
            etFirstName.setError("First Name required");
            etFirstName.requestFocus();
            return;
        }
        if(lastName.isEmpty()){
            etLastName.setError("Last Name required");
            etLastName.requestFocus();
            return;
        }
        if(streetAddress.isEmpty()){
            etStreetAddress.setError("Street Address required");
            etStreetAddress.requestFocus();
            return;
        }
        if(city.isEmpty()){
            etCity.setError("City required");
            etCity.requestFocus();
            return;
        }
        if(state.isEmpty()){
            etState.setError("State required");
            etState.requestFocus();
            return;
        }
        if(zip.isEmpty()){
            etZip.setError("Zip Code required");
            etZip.requestFocus();
            return;
        }
        if(taxId.isEmpty()){
            etTaxId.setError("Tax ID required");
            etTaxId.requestFocus();
            return;
        }
        if(position.isEmpty()){
            etPosition.setError("Position required");
            etPosition.requestFocus();
            return;
        }

        class SaveEmployee extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {

                Employee anEmployee = new Employee(firstName, lastName, streetAddress, city, state,
                        zip, taxId, position, department);
                ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).getShieldAgentsDatabase().
                        employeeDao().insertEmployee(anEmployee);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), EmployeeListActivity.class));
                Toast.makeText(getApplicationContext(), "New Agent Saved", Toast.LENGTH_SHORT).show();
            }
        }

        SaveEmployee saveEmployee = new SaveEmployee();
        saveEmployee.execute();
    }

    private void loadSpinners() {
        class LoadSpinners extends AsyncTask<Void, Void, List<Department>>{

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
