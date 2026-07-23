import axios from 'axios';
import GitClient from './GitClient';

/* HOL 19: Unit tests with Jest mocking.
   jest.mock('axios') replaces the real axios module with a Jest mock object.
   This means no real HTTP call is made to api.github.com during tests.
   The mock lets us control what axios.get() returns, so we can test our
   GitClient logic in complete isolation.                                      */
jest.mock('axios');

describe('Git Client Tests', () => {

  test('should return repository names for techiesyed', () => {
    /* Arrange: set up dummy API response data.
       axios.get.mockResolvedValue() makes the mock return a resolved Promise
       with our dummy data instead of making a real HTTP request.             */
    const dummyRepos = [
      { name: 'react-demo'    },
      { name: 'spring-learn'  },
      { name: 'docker-samples'},
    ];
    axios.get.mockResolvedValue({ data: dummyRepos });

    /* Act: call the method we want to test */
    const client = new GitClient();
    return client.getRepositories('techiesyed').then(repoNames => {
      /* Assert: verify the method returned mapped repo names, not raw objects */
      expect(repoNames).toEqual(['react-demo', 'spring-learn', 'docker-samples']);
      expect(repoNames).toHaveLength(3);

      /* Verify axios.get was called with the correct URL */
      expect(axios.get).toHaveBeenCalledWith(
        'https://api.github.com/users/techiesyed/repos'
      );
    });
  });

  test('should handle API errors gracefully', () => {
    /* Mock a rejected promise (simulates a network error) */
    axios.get.mockRejectedValue(new Error('Network Error'));

    const client = new GitClient();
    return client.getRepositories('invaliduser').catch(err => {
      expect(err.message).toBe('Network Error');
    });
  });

  test('should return empty array when user has no repositories', () => {
    axios.get.mockResolvedValue({ data: [] });

    const client = new GitClient();
    return client.getRepositories('emptyuser').then(repos => {
      expect(repos).toEqual([]);
      expect(repos).toHaveLength(0);
    });
  });
});
