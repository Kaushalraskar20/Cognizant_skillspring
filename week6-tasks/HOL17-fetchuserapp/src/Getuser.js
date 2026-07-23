import React, { Component } from 'react';

/* HOL 17: Getuser — fetches user data from https://api.randomuser.me/
   Uses componentDidMount() to trigger the API call AFTER the initial render.
   This is the correct lifecycle method for data fetching — the component is
   mounted in the DOM so we know we have a place to display the result.    */
class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user:    null,
      loading: true,
      error:   null,
    };
  }

  componentDidMount() {
    fetch('https://api.randomuser.me/')
      .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
      })
      .then(data => {
        /* The API returns { results: [{ name, picture, ... }] } */
        const user = data.results[0];
        this.setState({ user, loading: false });
      })
      .catch(error => {
        this.setState({ error: error.message, loading: false });
      });
  }

  render() {
    const { user, loading, error } = this.state;

    if (loading) return <p style={{ textAlign: 'center', padding: 40 }}>Loading user...</p>;
    if (error)   return <p style={{ color: 'red', padding: 20 }}>Error: {error}</p>;

    return (
      <div style={{ fontFamily: 'Arial', textAlign: 'center', padding: 40 }}>
        <h2>Random User</h2>
        {/* Display title and first name from the API response */}
        <p style={{ fontSize: 22, fontWeight: 'bold' }}>
          {user.name.title} {user.name.first} {user.name.last}
        </p>
        {/* Display profile image from the API response */}
        <img
          src={user.picture.large}
          alt="User"
          style={{ borderRadius: '50%', width: 150, height: 150, border: '4px solid #3498db' }}
        />
        <p>{user.email}</p>
        <p>{user.location.city}, {user.location.country}</p>
        <button onClick={() => this.setState({ loading: true }, () => this.componentDidMount())}
          style={{ padding: '8px 16px', background: '#2980b9', color: '#fff',
                   border: 'none', borderRadius: 5, cursor: 'pointer', marginTop: 10 }}>
          Load Another User
        </button>
      </div>
    );
  }
}

export default Getuser;
