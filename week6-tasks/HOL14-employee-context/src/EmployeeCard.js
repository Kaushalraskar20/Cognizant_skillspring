import React, { useContext } from 'react';
import ThemeContext from './ThemeContext';

/* HOL 14: EmployeeCard — CONSUMES the ThemeContext using useContext() hook.
   useContext(ThemeContext) reads the nearest ThemeContext.Provider value.
   No prop drilling — theme is not passed through EmployeesList at all.  */
function EmployeeCard({ employee }) {
  /* Retrieve the context value */
  const theme = useContext(ThemeContext);

  /* Button style changes based on theme context value */
  const buttonStyle = {
    padding: '6px 14px',
    border: 'none',
    borderRadius: 4,
    cursor: 'pointer',
    background: theme === 'dark' ? '#2c3e50' : '#3498db',
    color: '#fff',
  };

  const cardStyle = {
    border: '1px solid #ccc',
    borderRadius: 8,
    padding: 15,
    margin: 10,
    background: theme === 'dark' ? '#34495e' : '#ecf0f1',
    color: theme === 'dark' ? '#fff' : '#000',
    display: 'inline-block',
    width: 200,
  };

  return (
    <div className={theme} style={cardStyle}>
      <h4>{employee.name}</h4>
      <p>{employee.role}</p>
      <button style={buttonStyle}>View Profile</button>
    </div>
  );
}

export default EmployeeCard;
