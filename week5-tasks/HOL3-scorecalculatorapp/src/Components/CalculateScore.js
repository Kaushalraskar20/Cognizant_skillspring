import React from 'react';
import '../Stylesheets/mystyle.css';

/* HOL 3: Function component — accepts Name, School, Total, goal as props
   and calculates the average score as (Total / goal) * 100            */
function CalculateScore(props) {
  const { Name, School, Total, goal } = props;
  const averageScore = ((Total / goal) * 100).toFixed(2);

  return (
    <div className="score-container">
      <h2>Student Score Report</h2>
      <p><strong>Name  :</strong> {Name}</p>
      <p><strong>School:</strong> {School}</p>
      <p><strong>Total :</strong> {Total}</p>
      <p><strong>Goal  :</strong> {goal}</p>
      <p>Average Score: <span className="score-value">{averageScore}%</span></p>
    </div>
  );
}

export default CalculateScore;
