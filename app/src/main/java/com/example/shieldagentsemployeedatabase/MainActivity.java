package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //splash
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddDepartment:
                Intent addDepartmentIntent = new Intent(this, AddDepartmentActivity.class);
                startActivity(addDepartmentIntent);
                break;

            case R.id.btnViewDepartments:
                Intent viewDepartmentsIntent = new Intent(this, ViewDepartments.class);
                startActivity(viewDepartmentsIntent);
                break;

            case R.id.btnFilterEmployee:
                Intent filterEmployeeIntent = new Intent(this, FilterEmployeeActivity.class);
                startActivity(filterEmployeeIntent);
                break;
        }
    }
}
