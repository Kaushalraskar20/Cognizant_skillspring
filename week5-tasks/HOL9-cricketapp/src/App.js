import React, { Component } from 'react';

/* HOL 9: ListofPlayers — demonstrates ES6 map() and arrow function filter() */
function ListofPlayers() {
  /* ES6: const and array literal */
  const players = [
    { name: 'Rohit Sharma',   score: 83 },
    { name: 'Virat Kohli',    score: 71 },
    { name: 'KL Rahul',       score: 45 },
    { name: 'Shubman Gill',   score: 92 },
    { name: 'Hardik Pandya',  score: 58 },
    { name: 'Suryakumar',     score: 68 },
    { name: 'Ravindra Jadeja',score: 33 },
    { name: 'Jasprit Bumrah', score: 12 },
    { name: 'Mohammed Shami', score: 8  },
    { name: 'Kuldeep Yadav',  score: 22 },
    { name: 'Yuzvendra Chahal',score: 5 },
  ];

  /* ES6 arrow function: filter players with score below 70 */
  const lowScorers = players.filter(p => p.score < 70);

  return (
    <div>
      <h3>All Players (ES6 map)</h3>
      <ul>
        {/* ES6 map() with arrow function to transform array to JSX */}
        {players.map((p, i) => <li key={i}>{p.name} — {p.score}</li>)}
      </ul>
      <h3>Players Scoring Below 70 (ES6 filter + arrow function)</h3>
      <ul>
        {lowScorers.map((p, i) => <li key={i} style={{ color: 'red' }}>{p.name} — {p.score}</li>)}
      </ul>
    </div>
  );
}

/* HOL 9: IndianPlayers — demonstrates ES6 destructuring and spread operator */
function IndianPlayers() {
  /* ES6 array destructuring: extract elements by position */
  const teamPlayers = ['Rohit', 'Virat', 'KL Rahul', 'Gill', 'Pandya', 'Surya',
                       'Jadeja', 'Bumrah', 'Shami', 'Kuldeep', 'Chahal'];

  /* Destructuring to get odd and even players */
  const [first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh]
    = teamPlayers;
  const oddPlayers  = [first, third, fifth, seventh, ninth, eleventh];
  const evenPlayers = [second, fourth, sixth, eighth, tenth];

  /* ES6 spread operator (...) merges two arrays without mutating either */
  const T20players    = ['Rohit', 'Virat', 'Surya'];
  const RanjiPlayers  = ['Prithvi Shaw', 'Sarfaraz Khan', 'Musheer Khan'];
  const allPlayers    = [...T20players, ...RanjiPlayers]; /* Merge using spread */

  return (
    <div>
      <h3>Odd Position Players (ES6 Destructuring)</h3>
      <ul>{oddPlayers.map((p, i) => <li key={i}>{p}</li>)}</ul>

      <h3>Even Position Players (ES6 Destructuring)</h3>
      <ul>{evenPlayers.map((p, i) => <li key={i}>{p}</li>)}</ul>

      <h3>Merged T20 + Ranji Players (ES6 Spread Operator)</h3>
      <ul>{allPlayers.map((p, i) => <li key={i}>{p}</li>)}</ul>
    </div>
  );
}

class App extends Component {
  constructor(props) {
    super(props);
    /* flag controls which component renders */
    this.state = { flag: true };
  }

  render() {
    return (
      <div style={{ fontFamily: 'Arial', padding: 20 }}>
        <h1>Cricket App — ES6 Features</h1>
        <button onClick={() => this.setState({ flag: !this.state.flag })}
          style={{ marginBottom: 20, padding: '8px 16px' }}>
          Toggle (flag = {String(this.state.flag)})
        </button>

        {/* ES6 ternary (conditional) expression in JSX */}
        {this.state.flag ? <ListofPlayers /> : <IndianPlayers />}
      </div>
    );
  }
}

export default App;
