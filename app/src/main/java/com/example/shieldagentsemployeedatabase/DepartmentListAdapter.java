package com.example.shieldagentsemployeedatabase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shieldagentsemployeedatabase.entity.Department;

import java.util.List;

public class DepartmentListAdapter extends RecyclerView.Adapter<DepartmentListAdapter.ViewHolder> {
    List<Department> departments;

    public DepartmentListAdapter(List<Department> departments) {
        this.departments = departments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shield_department_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Department department = departments.get(position);

        holder.tvName.setText(department.getName());

        holder.setDepartment(department);
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        Department department;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(this);
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ViewDepartment.class);
            intent.putExtra("department", getDepartment());
            view.getContext().startActivity(intent);
        }
    }
}
