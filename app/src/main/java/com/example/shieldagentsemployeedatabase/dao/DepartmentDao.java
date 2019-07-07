package com.example.shieldagentsemployeedatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shieldagentsemployeedatabase.entity.Department;
import com.example.shieldagentsemployeedatabase.entity.Employee;

import java.util.List;

@Dao
public interface DepartmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDepartment(Department department);

    @Delete
    void deleteDepartment(Department department);

    @Update
    void updateDepartment(Department department);

    @Query("SELECT * from department_table")
    List<Department> getDepartments();

    @Query("SELECT * FROM department_table where id = :id")
    Department getById(int id);

}
