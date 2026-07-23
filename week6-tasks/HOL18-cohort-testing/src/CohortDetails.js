import React from 'react';

function CohortDetails({ cohort }) {
  const headingStyle = { color: cohort.status === 'ongoing' ? 'green' : 'blue' };
  return (
    <div className="cohort-card">
      <h3 style={headingStyle}>{cohort.code}</h3>
      <p>{cohort.name}</p>
      <p>Status: {cohort.status}</p>
      <p>Trainer: {cohort.trainer}</p>
    </div>
  );
}

export default CohortDetails;
