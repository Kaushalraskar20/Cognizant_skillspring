import React, { Component } from 'react';

/* HOL 11: Counter — increment/decrement with multiple method calls on click */
class Counter extends Component {
  constructor(props) {
    super(props);
    this.state = { count: 0, message: '' };
  }

  /* Increment calls TWO methods (as required: "Increase button invokes multiple methods") */
  increment = () => {
    this.updateCount(1);
    this.sayHello();
  };

  updateCount(delta) {
    this.setState(prev => ({ count: prev.count + delta }));
  }

  sayHello() {
    this.setState({ message: 'Hello! Count was just incremented.' });
  }

  decrement = () => {
    this.setState(prev => ({ count: prev.count - 1, message: '' }));
  };

  /* Arrow function with argument — passed via wrapper arrow in JSX */
  sayWelcome = (msg) => {
    alert(msg);
  };

  /* Synthetic event: SyntheticEvent wraps the native browser event
     so React can handle events consistently across all browsers.   */
  handleOnPress = (event) => {
    /* event.target gives the DOM element that was clicked */
    alert('I was clicked — target: ' + event.target.tagName);
  };

  render() {
    return (
      <div style={{ padding: 20, fontFamily: 'Arial', borderBottom: '2px solid #ccc' }}>
        <h2>Event Handling Demo</h2>
        <p>Counter: <strong>{this.state.count}</strong></p>
        {this.state.message && <p style={{ color: 'green' }}>{this.state.message}</p>}

        {/* Increment button calls two methods */}
        <button onClick={this.increment} style={btnStyle('#27ae60')}>Increment</button>

        {/* Decrement button */}
        <button onClick={this.decrement} style={btnStyle('#e74c3c')}>Decrement</button>

        {/* Say Welcome: passing an argument via wrapper arrow function
            (you cannot call sayWelcome("welcome") directly in onClick
             because that would invoke it immediately during render)   */}
        <button onClick={() => this.sayWelcome('Welcome!')} style={btnStyle('#2980b9')}>
          Say Welcome
        </button>

        {/* Synthetic event — receives SyntheticEvent object */}
        <button onClick={this.handleOnPress} style={btnStyle('#8e44ad')}>
          OnPress (Synthetic Event)
        </button>
      </div>
    );
  }
}

/* HOL 11: CurrencyConvertor — converts INR to EUR on button click */
class CurrencyConvertor extends Component {
  constructor(props) {
    super(props);
    this.state = { inr: '', euro: null };
  }

  handleChange = (event) => {
    this.setState({ inr: event.target.value, euro: null });
  };

  /* handleSubmit: event.preventDefault() stops the form from doing a page reload */
  handleSubmit = (event) => {
    event.preventDefault();
    const rate = 0.011; /* 1 INR ≈ 0.011 EUR */
    const euro = (parseFloat(this.state.inr) * rate).toFixed(4);
    this.setState({ euro });
  };

  render() {
    return (
      <div style={{ padding: 20, fontFamily: 'Arial' }}>
        <h2>Currency Convertor (INR → EUR)</h2>
        <form onSubmit={this.handleSubmit}>
          <input
            type="number"
            placeholder="Enter amount in INR"
            value={this.state.inr}
            onChange={this.handleChange}
            style={{ padding: 8, width: 200, marginRight: 10 }}
          />
          <button type="submit" style={btnStyle('#e67e22')}>Convert</button>
        </form>
        {this.state.euro !== null && (
          <p>₹{this.state.inr} = <strong>€{this.state.euro}</strong></p>
        )}
      </div>
    );
  }
}

const btnStyle = (bg) => ({
  margin: 8, padding: '8px 16px',
  background: bg, color: '#fff',
  border: 'none', borderRadius: 5, cursor: 'pointer'
});

function App() {
  return (
    <div>
      <h1 style={{ fontFamily: 'Arial', padding: '10px 20px', background: '#2c3e50', color: '#fff' }}>
        Event Examples App
      </h1>
      <Counter />
      <CurrencyConvertor />
    </div>
  );
}

export default App;
