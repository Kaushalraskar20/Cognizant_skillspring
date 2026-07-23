import React, { Component } from 'react';

/* HOL 16: Register — form with real-time field validation.
   Validation rules:
     Name     → at least 5 characters
     Email    → must contain @ and .
     Password → at least 8 characters                             */
class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name:     '',
      email:    '',
      password: '',
      errors:   { name: '', email: '', password: '' },
      submitted: false,
    };
  }

  /* validate — returns an errors object for current field values */
  validate(name, email, password) {
    const errors = { name: '', email: '', password: '' };

    if (name.length < 5)
      errors.name = 'Name must have at least 5 characters.';

    if (!email.includes('@') || !email.includes('.'))
      errors.email = 'Email must contain @ and .';

    if (password.length < 8)
      errors.password = 'Password must have at least 8 characters.';

    return errors;
  }

  /* handleChange — validates on every keystroke (real-time feedback) */
  handleChange = (event) => {
    const { name, value } = event.target;
    const updated = { ...this.state, [name]: value };
    const errors  = this.validate(updated.name, updated.email, updated.password);
    this.setState({ [name]: value, errors });
  };

  /* handleSubmit — final check before accepting the form */
  handleSubmit = (event) => {
    event.preventDefault();
    const { name, email, password } = this.state;
    const errors = this.validate(name, email, password);
    const isValid = Object.values(errors).every(e => e === '');

    if (isValid) {
      this.setState({ submitted: true });
    } else {
      this.setState({ errors });
    }
  };

  render() {
    const { name, email, password, errors, submitted } = this.state;

    if (submitted) {
      return (
        <div style={containerStyle}>
          <h2 style={{ color: '#27ae60' }}>✅ Registration Successful!</h2>
          <p>Welcome, <strong>{name}</strong>. Your account has been created.</p>
        </div>
      );
    }

    return (
      <div style={containerStyle}>
        <h2>Register</h2>
        <form onSubmit={this.handleSubmit} noValidate>
          <Field label="Name" name="name" type="text" value={name}
                 error={errors.name} onChange={this.handleChange}
                 placeholder="Enter name (min 5 chars)" />

          <Field label="Email" name="email" type="email" value={email}
                 error={errors.email} onChange={this.handleChange}
                 placeholder="Enter email (must have @ and .)" />

          <Field label="Password" name="password" type="password" value={password}
                 error={errors.password} onChange={this.handleChange}
                 placeholder="Enter password (min 8 chars)" />

          <button type="submit" style={submitBtn}>Register</button>
        </form>
      </div>
    );
  }
}

/* Reusable field component */
function Field({ label, name, type, value, error, onChange, placeholder }) {
  return (
    <div style={{ marginBottom: 15 }}>
      <label style={{ display: 'block', marginBottom: 4 }}>{label}:</label>
      <input type={type} name={name} value={value} onChange={onChange}
             placeholder={placeholder}
             style={{ width: '100%', padding: 8, boxSizing: 'border-box',
                      border: error ? '2px solid #e74c3c' : '1px solid #ccc',
                      borderRadius: 4 }} />
      {error && <p style={{ color: '#e74c3c', margin: '4px 0 0', fontSize: 13 }}>{error}</p>}
    </div>
  );
}

const containerStyle = {
  fontFamily: 'Arial', maxWidth: 400,
  margin: '40px auto', padding: 20,
  border: '1px solid #ccc', borderRadius: 8,
};

const submitBtn = {
  width: '100%', padding: '10px', background: '#2980b9',
  color: '#fff', border: 'none', borderRadius: 5,
  cursor: 'pointer', fontSize: 16,
};

export default Register;
