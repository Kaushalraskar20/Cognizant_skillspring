import React from 'react';
import styles from './CohortDetails.module.css';

/* HOL 5: CohortDetails — applies CSS Module class to container div.
   h3 colour is conditional: "ongoing" → green, everything else → blue. */
function CohortDetails({ cohort }) {
  /* Conditional inline style for the h3 heading */
  const headingStyle = {
    color: cohort.status === 'ongoing' ? 'green' : 'blue'
  };

  return (
    /* styles.box references the .box class from CohortDetails.module.css */
    <div className={styles.box}>
      <h3 style={headingStyle}>{cohort.code}</h3>
      <dl>
        <dt>Name</dt>    <dd>{cohort.name}</dd>
        <dt>Status</dt>  <dd>{cohort.status}</dd>
        <dt>Trainer</dt> <dd>{cohort.trainer}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
