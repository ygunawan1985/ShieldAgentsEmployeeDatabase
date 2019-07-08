package com.example.shieldagentsemployeedatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shieldagentsemployeedatabase.entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("UPDATE employee_table SET first_name = :firstName, last_name = :lastName, street_address = :streetAddress, " +
            "city = :city, state = :state, zip = :zip, tax_id = :taxId, position = :position, department = :department WHERE tax_id = :taxId")
    void updateEmployee(String firstName, String lastName, String streetAddress, String city, String state,
                        String zip, String taxId, String position, String department);

    @Query("SELECT * FROM employee_table where id = :id")
    Employee getById(int id);

    @Query("SELECT * from employee_table")
    List<Employee> getEmployees();

    @Query("SELECT * from employee_table WHERE department = :deptName")
    List<Employee> getEmployeesByDepartment(String deptName);

    @Query("DELETE from employee_table")
    void deleteEmployees();
}
