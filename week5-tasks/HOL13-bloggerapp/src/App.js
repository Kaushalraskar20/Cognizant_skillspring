import React, { Component } from 'react';

/* HOL 13: Three components with MULTIPLE conditional rendering techniques */

function BookDetails() {
  const books = [
    { id: 1, title: 'Clean Code',            author: 'Robert C. Martin', year: 2008 },
    { id: 2, title: 'The Pragmatic Programmer', author: 'Hunt & Thomas',  year: 1999 },
    { id: 3, title: 'Design Patterns',        author: 'GoF',              year: 1994 },
  ];
  return (
    <div style={cardStyle('#3498db')}>
      <h2>📚 Book Details</h2>
      {books.map(b => <p key={b.id}><strong>{b.title}</strong> by {b.author} ({b.year})</p>)}
    </div>
  );
}

function BlogDetails() {
  const blogs = [
    { id: 1, title: 'Getting Started with React',     date: '2024-01-15' },
    { id: 2, title: 'Understanding Spring Boot',       date: '2024-02-20' },
    { id: 3, title: 'Microservices Best Practices',   date: '2024-03-10' },
  ];
  return (
    <div style={cardStyle('#27ae60')}>
      <h2>📝 Blog Details</h2>
      {blogs.map(b => <p key={b.id}><strong>{b.title}</strong> — {b.date}</p>)}
    </div>
  );
}

function CourseDetails() {
  const courses = [
    { id: 1, name: 'Java FSE',      duration: '7 weeks', level: 'Advanced' },
    { id: 2, name: 'React Basics',  duration: '3 weeks', level: 'Beginner' },
    { id: 3, name: 'Spring Cloud',  duration: '2 weeks', level: 'Intermediate' },
  ];
  return (
    <div style={cardStyle('#e67e22')}>
      <h2>🎓 Course Details</h2>
      {courses.map(c => <p key={c.id}><strong>{c.name}</strong> — {c.duration} ({c.level})</p>)}
    </div>
  );
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { activeTab: 'books' };
  }

  render() {
    const { activeTab } = this.state;

    /* ── Technique 1: if/else with element variable ── */
    let content;
    if      (activeTab === 'books')   content = <BookDetails />;
    else if (activeTab === 'blogs')   content = <BlogDetails />;
    else                               content = <CourseDetails />;

    return (
      <div style={{ fontFamily: 'Arial', padding: 20 }}>
        <h1>Blogger App</h1>

        {/* Navigation buttons */}
        <div style={{ marginBottom: 20 }}>
          {['books', 'blogs', 'courses'].map(tab => (
            <button key={tab} onClick={() => this.setState({ activeTab: tab })}
              style={{ marginRight: 10, padding: '8px 16px',
                       background: activeTab === tab ? '#2c3e50' : '#bdc3c7',
                       color: activeTab === tab ? '#fff' : '#000',
                       border: 'none', borderRadius: 5, cursor: 'pointer' }}>
              {tab.charAt(0).toUpperCase() + tab.slice(1)}
            </button>
          ))}
        </div>

        {/* ── Technique 2: Ternary operator ── */}
        {activeTab === 'books'
          ? <BookDetails />
          /* ── Technique 3: nested ternary ── */
          : activeTab === 'blogs'
            ? <BlogDetails />
            : <CourseDetails />
        }

        <hr />
        <h3>All Components (Technique 4: &&amp; short-circuit)</h3>
        {/* ── Technique 4: && short-circuit evaluation ── */}
        {activeTab === 'books'   && <p>📚 Showing book list</p>}
        {activeTab === 'blogs'   && <p>📝 Showing blog list</p>}
        {activeTab === 'courses' && <p>🎓 Showing course list</p>}

        {/* Technique 1 result */}
        <h3>Element Variable Output</h3>
        {content}
      </div>
    );
  }
}

const cardStyle = (borderColor) => ({
  border: `2px solid ${borderColor}`,
  borderRadius: 8,
  padding: '10px 20px',
  marginBottom: 20,
});

export default App;
