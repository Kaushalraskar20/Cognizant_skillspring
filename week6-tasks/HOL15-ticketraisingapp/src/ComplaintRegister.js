import React, { Component } from 'react';

/* HOL 15: ComplaintRegister — controlled form component.
   "Controlled" means the form input values are stored in React state,
   not in the DOM. React is the single source of truth for the input values. */
class ComplaintRegister extends Component {
  constructor(props) {
    super(props);
    this.state = {
      employeeName: '',
      complaint:    '',
    };
  }

  /* handleChange — updates state on every keystroke (controlled component pattern) */
  handleChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value }); /* computed property name — works for both inputs */
  };

  /* handleSubmit — generates a reference number and shows it in an alert */
  handleSubmit = (event) => {
    event.preventDefault(); /* prevent default browser form submission (page reload) */

    if (!this.state.employeeName.trim() || !this.state.complaint.trim()) {
      alert('Please fill in all fields.');
      return;
    }

    /* Generate reference number: REF + timestamp */
    const refNumber = 'REF' + Date.now();
    alert(`Complaint submitted successfully!\nReference Number: ${refNumber}\nEmployee: ${this.state.employeeName}`);

    /* Reset form */
    this.setState({ employeeName: '', complaint: '' });
  };

  render() {
    return (
      <div style={{ fontFamily: 'Arial', maxWidth: 500, margin: '40px auto', padding: 20,
                    border: '1px solid #ccc', borderRadius: 8 }}>
        <h2>Complaint Register</h2>
        <form onSubmit={this.handleSubmit}>
          <div style={{ marginBottom: 15 }}>
            <label>Employee Name:</label><br />
            <input
              type="text"
              name="employeeName"
              value={this.state.employeeName}
              onChange={this.handleChange}
              placeholder="Enter your name"
              style={{ width: '100%', padding: 8, marginTop: 5, boxSizing: 'border-box' }}
            />
          </div>
          <div style={{ marginBottom: 15 }}>
            <label>Complaint:</label><br />
            <textarea
              name="complaint"
              value={this.state.complaint}
              onChange={this.handleChange}
              placeholder="Describe your complaint..."
              rows={5}
              style={{ width: '100%', padding: 8, marginTop: 5, boxSizing: 'border-box' }}
            />
          </div>
          <button type="submit"
            style={{ padding: '10px 20px', background: '#2980b9', color: '#fff',
                     border: 'none', borderRadius: 5, cursor: 'pointer' }}>
            Submit Complaint
          </button>
        </form>
      </div>
    );
  }
}

export default ComplaintRegister;
