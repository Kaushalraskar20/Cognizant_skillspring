import React from 'react';
import EmployeeCard from './EmployeeCard';

/* HOL 14: EmployeesList — no longer receives or passes theme as a prop.
   Theme is consumed directly by EmployeeCard via useContext().
   This eliminates "prop drilling" (passing props through intermediary components). */
function EmployeesList({ employees }) {
  return (
    <div>
      <h2>Employees</h2>
      {employees.map(emp => (
        <EmployeeCard key={emp.id} employee={emp} />
      ))}
    </div>
  );
}

export default EmployeesList;
