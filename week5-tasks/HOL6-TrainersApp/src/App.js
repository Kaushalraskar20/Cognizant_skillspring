import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import TrainersList from './TrainersList';
import TrainerDetails from './TrainerDetails';
import TrainersMock from './TrainersMock';

/* HOL 6: App — wraps everything in <Router> and defines route paths.
   <Routes> renders the FIRST <Route> whose path matches the current URL. */
function App() {
  return (
    <Router>
      <div>
        <h1 style={{ fontFamily: 'Arial', padding: '10px 20px', background: '#2c3e50', color: '#fff' }}>
          Trainers Portal
        </h1>
        <Routes>
          <Route path="/"               element={<TrainersList trainers={TrainersMock} />} />
          <Route path="/trainers/:id"   element={<TrainerDetails trainers={TrainersMock} />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
