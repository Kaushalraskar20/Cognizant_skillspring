import React, { Component } from 'react';
import ThemeContext from './ThemeContext';
import EmployeesList from './EmployeesList';

const employees = [
  { id: 1, name: 'Alice Johnson', role: 'Frontend Developer' },
  { id: 2, name: 'Bob Smith',     role: 'Backend Developer'  },
  { id: 3, name: 'Carol White',   role: 'DevOps Engineer'    },
  { id: 4, name: 'Dave Brown',    role: 'QA Engineer'        },
];

/* HOL 14: App — PROVIDES the ThemeContext value to all children.
   ThemeContext.Provider's value prop overrides the default 'light' value
   for all components nested inside it that call useContext(ThemeContext). */
class App extends Component {
  constructor(props) {
    super(props);
    this.state = { theme: 'light' };
  }

  toggleTheme = () => {
    this.setState(prev => ({ theme: prev.theme === 'light' ? 'dark' : 'light' }));
  };

  render() {
    return (
      /* Wrap the entire app in the Provider.
         value={this.state.theme} supplies the current theme to all consumers. */
      <ThemeContext.Provider value={this.state.theme}>
        <div style={{
          fontFamily: 'Arial', padding: 20,
          background: this.state.theme === 'dark' ? '#2c3e50' : '#fff',
          color:      this.state.theme === 'dark' ? '#fff'    : '#000',
          minHeight: '100vh'
        }}>
          <h1>Employee Management</h1>
          <p>Current theme: <strong>{this.state.theme}</strong></p>
          <button onClick={this.toggleTheme}
            style={{ padding: '8px 16px', marginBottom: 20,
                     background: '#e67e22', color: '#fff', border: 'none',
                     borderRadius: 5, cursor: 'pointer' }}>
            Toggle Theme (light / dark)
          </button>

          {/* EmployeesList does NOT receive theme as a prop — it's provided via context */}
          <EmployeesList employees={employees} />
        </div>
      </ThemeContext.Provider>
    );
  }
}

export default App;
