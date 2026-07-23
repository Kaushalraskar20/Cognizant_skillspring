import React, { Component } from 'react';

/* HOL 12: Conditional rendering — three techniques demonstrated:
   1. if/else with element variables
   2. Ternary operator in JSX
   3. && short-circuit evaluation                                    */

/* GuestPage — shown when user is NOT logged in */
function GuestPage() {
  return (
    <div style={{ padding: 20 }}>
      <h2>Available Flights (Guest View)</h2>
      <table border="1" cellPadding="10" style={{ borderCollapse: 'collapse' }}>
        <thead style={{ background: '#2c3e50', color: '#fff' }}>
          <tr><th>Flight</th><th>From</th><th>To</th><th>Departure</th><th>Price</th></tr>
        </thead>
        <tbody>
          <tr><td>AI-202</td><td>Mumbai</td><td>Delhi</td><td>08:00</td><td>₹5,499</td></tr>
          <tr><td>SG-405</td><td>Bangalore</td><td>Chennai</td><td>10:30</td><td>₹3,299</td></tr>
          <tr><td>6E-101</td><td>Hyderabad</td><td>Kolkata</td><td>14:00</td><td>₹4,799</td></tr>
        </tbody>
      </table>
      <p style={{ color: '#7f8c8d' }}>Please log in to book tickets.</p>
    </div>
  );
}

/* UserPage — shown when user IS logged in */
function UserPage({ username }) {
  return (
    <div style={{ padding: 20 }}>
      <h2>Welcome, {username}!</h2>
      <p>You can now book tickets for the following flights:</p>
      <table border="1" cellPadding="10" style={{ borderCollapse: 'collapse' }}>
        <thead style={{ background: '#27ae60', color: '#fff' }}>
          <tr><th>Flight</th><th>From</th><th>To</th><th>Departure</th><th>Price</th><th>Action</th></tr>
        </thead>
        <tbody>
          <tr><td>AI-202</td><td>Mumbai</td><td>Delhi</td><td>08:00</td><td>₹5,499</td>
            <td><button style={{ background: '#27ae60', color: '#fff', border: 'none', padding: '4px 10px', borderRadius: 4, cursor: 'pointer' }}>Book</button></td></tr>
          <tr><td>SG-405</td><td>Bangalore</td><td>Chennai</td><td>10:30</td><td>₹3,299</td>
            <td><button style={{ background: '#27ae60', color: '#fff', border: 'none', padding: '4px 10px', borderRadius: 4, cursor: 'pointer' }}>Book</button></td></tr>
          <tr><td>6E-101</td><td>Hyderabad</td><td>Kolkata</td><td>14:00</td><td>₹4,799</td>
            <td><button style={{ background: '#27ae60', color: '#fff', border: 'none', padding: '4px 10px', borderRadius: 4, cursor: 'pointer' }}>Book</button></td></tr>
        </tbody>
      </table>
    </div>
  );
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { isLoggedIn: false, username: 'Piyush' };
  }

  login  = () => this.setState({ isLoggedIn: true  });
  logout = () => this.setState({ isLoggedIn: false });

  render() {
    const { isLoggedIn, username } = this.state;

    /* Technique 1: element variable — assign JSX to a variable before return */
    let page;
    if (isLoggedIn) {
      page = <UserPage username={username} />;
    } else {
      page = <GuestPage />;
    }

    return (
      <div style={{ fontFamily: 'Arial' }}>
        <div style={{ padding: '10px 20px', background: '#2c3e50', color: '#fff', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <h1 style={{ margin: 0 }}>Flight Ticket Booking</h1>

          {/* Technique 2: ternary operator — inline conditional in JSX */}
          {isLoggedIn
            ? <button onClick={this.logout} style={btnStyle('#e74c3c')}>Logout</button>
            : <button onClick={this.login}  style={btnStyle('#27ae60')}>Login</button>
          }
        </div>

        {/* Technique 3: && short-circuit — only renders when isLoggedIn is true */}
        {isLoggedIn && <p style={{ padding: '5px 20px', background: '#d5f5e3' }}>You are logged in as <strong>{username}</strong></p>}

        {/* Render the element variable */}
        {page}
      </div>
    );
  }
}

const btnStyle = (bg) => ({
  padding: '8px 16px', background: bg, color: '#fff',
  border: 'none', borderRadius: 5, cursor: 'pointer'
});

export default App;
