import React from 'react';
import ComplaintRegister from './ComplaintRegister';

function App() {
  return (
    <div>
      <h1 style={{ fontFamily: 'Arial', textAlign: 'center', padding: 20,
                   background: '#2c3e50', color: '#fff', margin: 0 }}>
        Ticket Raising System
      </h1>
      <ComplaintRegister />
    </div>
  );
}

export default App;
