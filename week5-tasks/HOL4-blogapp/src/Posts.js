import React, { Component } from 'react';

/* HOL 4: Posts component — fetches posts using Fetch API in componentDidMount */
class Posts extends Component {

  constructor(props) {
    super(props);
    /* Initialize state with an empty posts array */
    this.state = { posts: [], hasError: false };
  }

  /* componentDidMount fires AFTER the component renders for the first time.
     Best place to make network requests — DOM is ready, no blocking render. */
  componentDidMount() {
    this.loadPosts();
  }

  /* Fetch posts from the JSONPlaceholder API and update state */
  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => response.json())
      .then(data => {
        this.setState({ posts: data });
      })
      .catch(err => alert('Error loading posts: ' + err.message));
  }

  /* componentDidCatch catches errors thrown in child component trees.
     Acts as an error boundary — prevents a child crash from breaking the whole UI. */
  componentDidCatch(error, info) {
    alert('Component error: ' + error.message);
    this.setState({ hasError: true });
  }

  render() {
    if (this.state.hasError) {
      return <h2>Something went wrong. Please refresh.</h2>;
    }

    if (this.state.posts.length === 0) {
      return <p>Loading posts...</p>;
    }

    return (
      <div style={{ maxWidth: 700, margin: '20px auto', fontFamily: 'Arial' }}>
        <h1>Blog Posts</h1>
        {this.state.posts.slice(0, 10).map(post => (
          <div key={post.id} style={{ marginBottom: 20, borderBottom: '1px solid #ccc', paddingBottom: 10 }}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
