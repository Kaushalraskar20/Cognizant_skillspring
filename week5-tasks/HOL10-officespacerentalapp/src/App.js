import React from 'react';

/* HOL 10: Office object model */
const offices = [
  { id: 1, name: 'Prestige Tech Park',  rent: 55000, address: 'Marathahalli, Bangalore' },
  { id: 2, name: 'Cyber Pearl',         rent: 80000, address: 'Hi-Tech City, Hyderabad' },
  { id: 3, name: 'Mindspace',           rent: 40000, address: 'Airoli, Mumbai'           },
  { id: 4, name: 'DLF Cyber City',      rent: 95000, address: 'Gurgaon, Delhi NCR'       },
  { id: 5, name: 'Tidewater Tech Park', rent: 30000, address: 'Kochi, Kerala'            },
];

/* HOL 10: App — demonstrates JSX expressions, attributes, objects, list rendering,
   and conditional inline CSS (rent colour based on value).                      */
function App() {
  /* JSX element for the heading */
  const heading = <h1 style={{ fontFamily: 'Arial', color: '#2c3e50' }}>Office Space Rental Portal</h1>;

  /* JSX attribute — image displayed via src attribute */
  const officeImage = (
    <img
      src="https://images.unsplash.com/photo-1497366216548-37526070297c?w=600"
      alt="Office Space"
      style={{ width: '100%', maxWidth: 600, borderRadius: 8, display: 'block', margin: '10px 0' }}
    />
  );

  return (
    <div style={{ fontFamily: 'Arial', maxWidth: 700, margin: '0 auto', padding: 20 }}>
      {/* Render heading JSX element */}
      {heading}

      {/* Render image JSX attribute demo */}
      {officeImage}

      <h2>Available Office Spaces</h2>
      {/* Loop through office list — key prop required for list items */}
      {offices.map(office => (
        <div key={office.id}
          style={{ border: '1px solid #ccc', borderRadius: 8, padding: 15, marginBottom: 15 }}>
          <h3>{office.name}</h3>
          <p><strong>Address :</strong> {office.address}</p>
          {/* Conditional inline CSS: rent colour — red if below 60000, green if above */}
          <p>
            <strong>Rent (₹) : </strong>
            <span style={{ color: office.rent < 60000 ? 'red' : 'green', fontWeight: 'bold' }}>
              ₹{office.rent.toLocaleString()}
            </span>
          </p>
        </div>
      ))}
    </div>
  );
}

export default App;
