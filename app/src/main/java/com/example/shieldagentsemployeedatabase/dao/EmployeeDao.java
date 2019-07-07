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

    @Update
    void update(Employee employee);

    @Query("SELECT * FROM employee_table where id = :id")
    Employee getById(int id);

    @Query("SELECT * from employee_table")
    List<Employee> getEmployees();

    @Query("DELETE from employee_table")
    void deleteEmployees();
}
