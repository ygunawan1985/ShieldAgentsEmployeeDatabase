package com.example.shieldagentsemployeedatabase.datasource.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shieldagentsemployeedatabase.dao.DepartmentDao;
import com.example.shieldagentsemployeedatabase.dao.EmployeeDao;
import com.example.shieldagentsemployeedatabase.entity.Department;
import com.example.shieldagentsemployeedatabase.entity.Employee;

@Database(entities = {Employee.class, Department.class}, version = 6)
public abstract class ShieldAgentsDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();
    public abstract DepartmentDao departmentDao();

//    public static final String DATABASE_NAME = "ShieldAgentsDatabase";
//    private static volatile ShieldAgentsDatabase INSTANCE;

//    static ShieldAgentsDatabase getDatabase(final Context context){
//        if(INSTANCE == null){
//            synchronized (ShieldAgentsDatabase.class) {
//                if(INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShieldAgentsDatabase.class, DATABASE_NAME).build();
//                }
//            }
//        }
//
//        return INSTANCE;
//    }


}
