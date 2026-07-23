import React, { Component } from 'react';
import GitClient from './GitClient';

/* HOL 19: App — uses GitClient to fetch and display GitHub repositories */
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      repos:    [],
      loading:  true,
      error:    null,
      username: 'techiesyed',
    };
    this.gitClient = new GitClient();
  }

  componentDidMount() {
    this.fetchRepos(this.state.username);
  }

  fetchRepos(username) {
    this.setState({ loading: true, error: null });
    this.gitClient
      .getRepositories(username)
      .then(repos => this.setState({ repos, loading: false }))
      .catch(err  => this.setState({ error: err.message, loading: false }));
  }

  handleSearch = (e) => {
    e.preventDefault();
    this.fetchRepos(this.state.username);
  };

  render() {
    const { repos, loading, error, username } = this.state;
    return (
      <div style={{ fontFamily: 'Arial', maxWidth: 600, margin: '0 auto', padding: 20 }}>
        <h1>GitHub Repository Viewer</h1>
        <form onSubmit={this.handleSearch} style={{ marginBottom: 20 }}>
          <input
            type="text"
            value={username}
            onChange={e => this.setState({ username: e.target.value })}
            placeholder="GitHub username"
            style={{ padding: 8, width: 300, marginRight: 10 }}
          />
          <button type="submit"
            style={{ padding: '8px 16px', background: '#2c3e50', color: '#fff',
                     border: 'none', borderRadius: 4, cursor: 'pointer' }}>
            Search
          </button>
        </form>

        {loading && <p>Loading repositories...</p>}
        {error   && <p style={{ color: 'red' }}>Error: {error}</p>}
        {!loading && !error && (
          <>
            <h3>Repositories for <em>{username}</em> ({repos.length})</h3>
            <ul>
              {repos.map((repo, i) => <li key={i} style={{ padding: '4px 0' }}>{repo}</li>)}
            </ul>
          </>
        )}
      </div>
    );
  }
}

export default App;
