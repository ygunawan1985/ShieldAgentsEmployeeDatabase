package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FilterEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_employee);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddNewEmployee:
                Intent addNewEmployeeIntent = new Intent(this, NewEmployeeActivity.class);
                startActivity(addNewEmployeeIntent);
                break;
            case R.id.btnDeleteEmployee:
                Intent deleteEmployeeIntent = new Intent(this, DeleteEmployeeActivity.class);
                startActivity(deleteEmployeeIntent);
                break;
            case R.id.btnUpdateEmployee:
                Intent updateEmployeeIntent = new Intent(this, UpdateEmployeeActivity.class);
                startActivity(updateEmployeeIntent);
                break;
            case R.id.btnViewEmployees:
                Intent viewEmployeeListIntent = new Intent(this, EmployeeListActivity.class);
                startActivity(viewEmployeeListIntent);
                break;
        }

    }
}
