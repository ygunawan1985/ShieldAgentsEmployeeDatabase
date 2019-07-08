package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shieldagentsemployeedatabase.datasource.local.database.ShieldAgentsDatabaseClient;
import com.example.shieldagentsemployeedatabase.entity.Department;
import com.example.shieldagentsemployeedatabase.entity.Employee;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DeleteEmployeeActivity extends AppCompatActivity {

    private TextView tvAgentName;
    private TextView tvFirstName, tvLastName, tvStreetAddress, tvCity, tvState, tvZip, tvTaxId, tvPosition;
    private Spinner spinnerDepartments;
    private Intent receivedIntent;
    Employee receivedEmployee;
    ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);

        tvAgentName = findViewById(R.id.tvAgentName);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvStreetAddress = findViewById(R.id.tvStreetAddress);
        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvZip = findViewById(R.id.tvZip);
        tvTaxId = findViewById(R.id.tvTaxId);
        tvPosition = findViewById(R.id.tvPosition);
        spinnerDepartments = findViewById(R.id.spinnerDepartments);
        loadSpinnersAndDetails();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDeleteAgent:
                deleteEmployee();
                break;
            case R.id.btnCancel:
                startActivity(new Intent(this, EmployeeListActivity.class));
                break;
        }
    }

    private void deleteEmployee(){
//        final String firstName = tvFirstName.getText().toString();
//        final String lastName = tvLastName.getText().toString();
//        final String streetAddress = tvStreetAddress.getText().toString();
//        final String city = tvCity.getText().toString();
//        final String state = tvState.getText().toString();
//        final String zip = tvZip.getText().toString();
//        final String taxId = tvTaxId.getText().toString();
//        final String position = tvPosition.getText().toString();
//        final String department = String.valueOf(spinnerDepartments.getSelectedItem());

        class DeleteEmployee extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //Employee anEmployee = new Employee(firstName, lastName, streetAddress, city, state, zip, taxId, position, department);
                ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).getShieldAgentsDatabase().
                        employeeDao().deleteEmployee(receivedEmployee);

                //ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).getShieldAgentsDatabase().
                //        employeeDao().updateEmployee(firstName, lastName, streetAddress, city, state, zip, taxId, position, department);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), EmployeeListActivity.class));
                Toast.makeText(getApplicationContext(), tvAgentName.getText() + " is deleted!", Toast.LENGTH_SHORT).show();
            }
        }

        DeleteEmployee deleteEmployee = new DeleteEmployee();
        deleteEmployee.execute();
    }

    private void loadSpinnersAndDetails() {
        class LoadSpinnersAndDetails extends AsyncTask<Void, Void, List<Department>> {

            @Override
            protected List<Department> doInBackground(Void... voids) {
                List<Department> departmentList = ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).
                        getShieldAgentsDatabase().departmentDao().getDepartments();

                receivedIntent = getIntent();
                Bundle receivedBundle = receivedIntent.getExtras();
                receivedEmployee = receivedBundle.getParcelable("employee");

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

                    dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, strList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDepartments.setAdapter(dataAdapter);
                }

                if(receivedEmployee != null){
                    tvAgentName.setText(receivedEmployee.getFirstName() + " " + receivedEmployee.getLastName());
                    tvFirstName.setText(receivedEmployee.getFirstName());
                    tvLastName.setText(receivedEmployee.getLastName());
                    tvStreetAddress.setText(receivedEmployee.getStreetAddress());
                    tvCity.setText(receivedEmployee.getCity());
                    tvState.setText(receivedEmployee.getState());
                    tvZip.setText(receivedEmployee.getZip());
                    tvTaxId.setText(receivedEmployee.getTaxId());
                    tvPosition.setText(receivedEmployee.getPosition());
                    String strDepartment = receivedEmployee.getDepartment();
                    spinnerDepartments.setSelection(dataAdapter.getPosition(strDepartment));
                }

            }
        }

        LoadSpinnersAndDetails loadSpinnersAndDetails = new LoadSpinnersAndDetails();
        loadSpinnersAndDetails.execute();
    }
}
