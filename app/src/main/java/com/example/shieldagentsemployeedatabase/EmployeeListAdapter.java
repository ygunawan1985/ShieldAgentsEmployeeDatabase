package com.example.shieldagentsemployeedatabase;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shieldagentsemployeedatabase.entity.Employee;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {
    List<Employee> employees;

    public EmployeeListAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.shield_employee_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employees.get(position);

        holder.tvFirstName.setText(employee.getFirstName());
        holder.tvLastName.setText(employee.getLastName());
        holder.tvStreetAddress.setText(employee.getStreetAddress());
        holder.tvCity.setText(employee.getCity());
        holder.tvState.setText(employee.getState());
        holder.tvZip.setText(employee.getZip());
        holder.tvTaxId.setText(employee.getTaxId());
        holder.tvPosition.setText(employee.getPosition());
        holder.tvDepartment.setText(employee.getDepartment());

        holder.setEmployee(employee);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvFirstName, tvLastName, tvStreetAddress, tvCity, tvState, tvZip, tvTaxId, tvPosition, tvDepartment;
        Employee employee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvStreetAddress = itemView.findViewById(R.id.tvStreetAddress);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvState = itemView.findViewById(R.id.tvState);
            tvZip = itemView.findViewById(R.id.tvZip);
            tvTaxId = itemView.findViewById(R.id.tvTaxId);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);
            itemView.setOnClickListener(this);
        }

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), EmployeeDetailsActivity.class);
            intent.putExtra("employee", getEmployee());
            view.getContext().startActivity(intent);
        }
    }
}
