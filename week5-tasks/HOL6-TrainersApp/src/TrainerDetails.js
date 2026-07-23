import React from 'react';
import { useParams, Link } from 'react-router-dom';

/* HOL 6: TrainerDetails — reads :id from the URL via useParams()
   and displays the matching trainer's full details.                */
function TrainerDetails({ trainers }) {
  const { id } = useParams();
  const trainer = trainers.find(t => t.TrainerId === parseInt(id));

  if (!trainer) {
    return <p>Trainer not found. <Link to="/">Back</Link></p>;
  }

  return (
    <div style={{ fontFamily: 'Arial', padding: 20 }}>
      <h2>{trainer.Name}</h2>
      <p><strong>ID         :</strong> {trainer.TrainerId}</p>
      <p><strong>Email      :</strong> {trainer.Email}</p>
      <p><strong>Phone      :</strong> {trainer.Phone}</p>
      <p><strong>Technology :</strong> {trainer.Technology}</p>
      <p><strong>Skills     :</strong> {trainer.Skills.join(', ')}</p>
      <Link to="/">← Back to List</Link>
    </div>
  );
}

export default TrainerDetails;
