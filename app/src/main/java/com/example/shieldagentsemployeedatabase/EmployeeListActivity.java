package com.example.shieldagentsemployeedatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.shieldagentsemployeedatabase.datasource.local.database.ShieldAgentsDatabaseClient;
import com.example.shieldagentsemployeedatabase.entity.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rvEmployees;
    Intent receivedIntent;
    String departmentSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        rvEmployees = findViewById(R.id.rvEmployees);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        receivedIntent = getIntent();

        if(receivedIntent != null){
            departmentSelected = receivedIntent.getStringExtra("filter");
        }

        getEmployees();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employee_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_new) {
            startActivity(new Intent(this, NewEmployeeActivity.class));
        } else if (id == R.id.nav_delete) {

        } else if (id == R.id.nav_update) {

        } else if (id == R.id.nav_filter) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getEmployees() {

        class GetEmployees extends AsyncTask<Void, Void, List<Employee>> {

            @Override
            protected List<Employee> doInBackground(Void... voids) {
                List<Employee> employeeList;

                if(departmentSelected != null){
                    employeeList = ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).
                            getShieldAgentsDatabase().employeeDao().getEmployeesByDepartment(departmentSelected);
                } else {
                    employeeList = ShieldAgentsDatabaseClient.getInstance(getApplicationContext()).
                            getShieldAgentsDatabase().employeeDao().getEmployees();
                }

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
