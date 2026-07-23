import React from 'react';
import CohortDetails from './CohortDetails';
import { CohortData } from './Cohort';

function App() {
  return (
    <div style={{ fontFamily: 'Arial', padding: 20 }}>
      <h1>Cohort Dashboard (HOL 18 - Testing)</h1>
      <p>Run <code>npm test</code> to execute the Jest unit tests.</p>
      {CohortData.map(c => <CohortDetails key={c.id} cohort={c} />)}
    </div>
  );
}

export default App;
