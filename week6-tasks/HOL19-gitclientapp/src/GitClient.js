import axios from 'axios';

/* HOL 19: GitClient — class that makes calls to the GitHub API.
   Separating the API logic into its own class makes it easy to MOCK
   in unit tests — we can replace axios with a fake that returns dummy data
   without ever hitting the real api.github.com.                           */
class GitClient {

  /**
   * Fetches the list of public repositories for a given GitHub username.
   * Returns a Promise that resolves to an array of repository names.
   */
  getRepositories(username) {
    return axios
      .get(`https://api.github.com/users/${username}/repos`)
      .then(response => response.data.map(repo => repo.name));
  }
}

export default GitClient;
