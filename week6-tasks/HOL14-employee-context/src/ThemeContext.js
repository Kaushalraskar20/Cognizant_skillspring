import { createContext } from 'react';

/* HOL 14: Create the ThemeContext with default value 'light'.
   createContext(defaultValue) — the default is only used when a component
   does NOT have a matching Provider above it in the tree.             */
const ThemeContext = createContext('light');

export default ThemeContext;
