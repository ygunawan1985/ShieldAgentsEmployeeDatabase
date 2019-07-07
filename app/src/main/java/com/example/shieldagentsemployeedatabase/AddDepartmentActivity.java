package com.example.shieldagentsemployeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shieldagentsemployeedatabase.datasource.local.database.ShieldAgentsDatabaseClient;
import com.example.shieldagentsemployeedatabase.entity.Department;

public class AddDepartmentActivity extends AppCompatActivity {

    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);

        etName = findViewById(R.id.etName);
    }

    public void onClick(View view) {
        saveDepartment();
    }

    private void saveDepartment() {
        final String name = etName.getText().toString();

        if(name.isEmpty()){
            etName.setError("Department name required");
            etName.requestFocus();
            return;
        }

        class SaveDepartment extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a Department
                Department department = new Department();
                department.setName(name);

                //adding to the database
                ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).getShieldAgentsDatabase().
                        departmentDao().insertDepartment(department);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "New Department Saved", Toast.LENGTH_SHORT).show();
            }
        }

        SaveDepartment saveDepartment = new SaveDepartment();
        saveDepartment.execute();
    }
}
