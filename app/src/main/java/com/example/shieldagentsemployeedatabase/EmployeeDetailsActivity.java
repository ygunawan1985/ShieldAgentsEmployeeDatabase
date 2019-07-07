package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shieldagentsemployeedatabase.entity.Employee;

public class EmployeeDetailsActivity extends AppCompatActivity {

    private TextView tvFirstName, tvLastName, tvStreetAddress, tvCity, tvState, tvZip, tvTaxId, tvPosition, tvDepartment;
    private Intent receivedIntent;
    private Employee receivedEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvStreetAddress = findViewById(R.id.tvStreetAddress);
        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvZip = findViewById(R.id.tvZip);
        tvTaxId = findViewById(R.id.tvTaxId);
        tvPosition = findViewById(R.id.tvPosition);
        tvDepartment = findViewById(R.id.tvDepartment);

        displayEmployee();
    }

    private void displayEmployee() {
        class DisplayEmployee extends AsyncTask<Void, Void, Employee>{

            @Override
            protected Employee doInBackground(Void... voids) {
                receivedIntent = getIntent();
                if(receivedIntent != null){
                    receivedEmployee = receivedIntent.getParcelableExtra("employee");
                }
                return receivedEmployee;
            }

            @Override
            protected void onPostExecute(Employee employee) {
                super.onPostExecute(employee);
                tvFirstName.setText(receivedEmployee.getFirstName());
                tvLastName.setText(receivedEmployee.getLastName());
                tvStreetAddress.setText(receivedEmployee.getStreetAddress());
                tvCity.setText(receivedEmployee.getCity());
                tvState.setText(receivedEmployee.getState());
                tvZip.setText(receivedEmployee.getZip());
                tvTaxId.setText(receivedEmployee.getTaxId());
                tvPosition.setText(receivedEmployee.getPosition());
                tvDepartment.setText(receivedEmployee.getDepartment());
            }
        }

        DisplayEmployee displayEmployee = new DisplayEmployee();
        displayEmployee.execute();

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUpdateEmployee:
                Intent updateIntent = new Intent(this, UpdateEmployeeActivity.class);
                Bundle updateBundle = new Bundle();
                updateBundle.putParcelable("employee", receivedEmployee);
                updateIntent.putExtras(updateBundle);
                startActivity(updateIntent);
                break;

            case R.id.btnDeleteEmployee:
                Intent deleteIntent = new Intent(this, DeleteEmployeeActivity.class);
                Bundle deleteBundle = new Bundle();
                deleteBundle.putParcelable("employee", receivedEmployee);
                deleteIntent.putExtras(deleteBundle);
                startActivity(deleteIntent);
                break;

            case R.id.btnHome:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
        }
    }
}
