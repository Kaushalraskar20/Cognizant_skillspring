import React from 'react';
import { render, screen } from '@testing-library/react';
import CohortDetails from './CohortDetails';
import { CohortData } from './Cohort';

/* HOL 18: Unit tests for CohortDetails component using @testing-library/react.
   describe() groups related tests into a named suite.
   test()     defines a single test case (also aliased as it()).
   expect()   + matchers assert conditions.                                    */

describe('Cohort Details Component', () => {

  /* Test 1: Component renders without crashing */
  test('should create the component', () => {
    render(<CohortDetails cohort={CohortData[0]} />);
    /* If render() throws, the test fails automatically */
    expect(true).toBe(true);
  });

  /* Test 2: Props are initialised correctly */
  test('should initialize the props', () => {
    const cohort = CohortData[0];
    render(<CohortDetails cohort={cohort} />);
    /* toBeInTheDocument() checks the element is present in the rendered DOM */
    expect(screen.getByText(cohort.name)).toBeInTheDocument();
    expect(screen.getByText(`Status: ${cohort.status}`)).toBeInTheDocument();
    expect(screen.getByText(`Trainer: ${cohort.trainer}`)).toBeInTheDocument();
  });

  /* Test 3: Cohort code is displayed in h3 */
  test('should display cohort code in h3', () => {
    const cohort = CohortData[0];
    render(<CohortDetails cohort={cohort} />);
    const heading = screen.getByRole('heading', { level: 3 });
    /* toHaveTextContent() checks the text content of an element */
    expect(heading).toHaveTextContent(cohort.code);
  });

  /* Test 4: Ongoing cohort renders green heading */
  test('should render green heading for ongoing cohort', () => {
    const ongoingCohort = CohortData.find(c => c.status === 'ongoing');
    render(<CohortDetails cohort={ongoingCohort} />);
    const heading = screen.getByRole('heading', { level: 3 });
    expect(heading).toHaveStyle({ color: 'green' });
  });

  /* Test 5: Completed cohort renders blue heading */
  test('should render blue heading for completed cohort', () => {
    const completedCohort = CohortData.find(c => c.status === 'completed');
    render(<CohortDetails cohort={completedCohort} />);
    const heading = screen.getByRole('heading', { level: 3 });
    expect(heading).toHaveStyle({ color: 'blue' });
  });
});
