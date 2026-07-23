import React, { Component } from 'react';

/* HOL 7: Cart — simple model class for a shopping item */
class Cart {
  constructor(Itemname, Price) {
    this.Itemname = Itemname;
    this.Price    = Price;
  }
}

/* HOL 7: OnlineShopping — class component that holds an array of Cart items.
   Props are passed via JSX attributes; here we render them as a table.
   Props flow ONE WAY: parent → child (you cannot change props from inside a component). */
class OnlineShopping extends Component {

  constructor(props) {
    super(props);
    /* Initialize 5 Cart items */
    this.items = [
      new Cart('Laptop',     75000),
      new Cart('Headphones',  3500),
      new Cart('Mouse',        999),
      new Cart('Keyboard',    2499),
      new Cart('Monitor',    18000),
    ];
  }

  render() {
    return (
      <div style={{ fontFamily: 'Arial', padding: 20 }}>
        <h2>Online Shopping Cart</h2>
        <table border="1" cellPadding="10" style={{ borderCollapse: 'collapse' }}>
          <thead>
            <tr style={{ background: '#2c3e50', color: '#fff' }}>
              <th>#</th>
              <th>Item Name</th>
              <th>Price (₹)</th>
            </tr>
          </thead>
          <tbody>
            {this.items.map((item, index) => (
              <tr key={index}>
                <td>{index + 1}</td>
                <td>{item.Itemname}</td>
                <td>{item.Price.toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

function App() {
  return <OnlineShopping />;
}

export default App;
