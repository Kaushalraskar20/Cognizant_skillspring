import React from 'react';
import { Link } from 'react-router-dom';

/* HOL 6: TrainersList — displays clickable trainer names using React Router <Link>.
   <Link to="/trainers/:id"> creates client-side navigation without a page reload. */
function TrainersList({ trainers }) {
  return (
    <div style={{ fontFamily: 'Arial', padding: 20 }}>
      <h2>Trainers List</h2>
      <ul>
        {trainers.map(trainer => (
          <li key={trainer.TrainerId} style={{ margin: '8px 0' }}>
            <Link to={`/trainers/${trainer.TrainerId}`}>{trainer.Name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TrainersList;
