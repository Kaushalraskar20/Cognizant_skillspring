import React from 'react';
import Getuser from './Getuser';

function App() {
  return (
    <div>
      <h1 style={{ fontFamily: 'Arial', textAlign: 'center', padding: 20,
                   background: '#2c3e50', color: '#fff', margin: 0 }}>
        Fetch User App
      </h1>
      <Getuser />
    </div>
  );
}

export default App;
