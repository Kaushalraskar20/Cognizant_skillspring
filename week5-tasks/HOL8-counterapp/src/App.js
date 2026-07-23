import React, { Component } from 'react';

/* HOL 8: CountPeople — demonstrates React state.
   State is mutable data INSIDE a component. Calling setState() causes re-render.
   Props are read-only; state is read-write (via setState only — never direct assignment). */
class CountPeople extends Component {

  constructor(props) {
    super(props);
    /* Initialize state with entry and exit counts */
    this.state = {
      entrycount: 0,
      exitcount:  0,
    };
    /* Bind methods to 'this' so they can access component instance */
    this.UpdateEntry = this.UpdateEntry.bind(this);
    this.UpdateExit  = this.UpdateExit.bind(this);
  }

  /* UpdateEntry — increments entry count by 1 */
  UpdateEntry() {
    this.setState({ entrycount: this.state.entrycount + 1 });
  }

  /* UpdateExit — increments exit count by 1 */
  UpdateExit() {
    this.setState({ exitcount: this.state.exitcount + 1 });
  }

  render() {
    return (
      <div style={{ fontFamily: 'Arial', textAlign: 'center', padding: 40 }}>
        <h2>Mall People Counter</h2>
        <p>People Entered : <strong>{this.state.entrycount}</strong></p>
        <p>People Exited  : <strong>{this.state.exitcount}</strong></p>
        <button onClick={this.UpdateEntry}
          style={{ margin: 10, padding: '10px 20px', background: '#27ae60', color: '#fff', border: 'none', borderRadius: 5, cursor: 'pointer' }}>
          Login (Entry)
        </button>
        <button onClick={this.UpdateExit}
          style={{ margin: 10, padding: '10px 20px', background: '#e74c3c', color: '#fff', border: 'none', borderRadius: 5, cursor: 'pointer' }}>
          Exit
        </button>
      </div>
    );
  }
}

function App() {
  return <CountPeople />;
}

export default App;
