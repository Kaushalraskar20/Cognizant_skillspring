/* HOL 4: Post class — represents a single blog post entity */
class Post {
  constructor(id, title, body) {
    this.id    = id;
    this.title = title;
    this.body  = body;
  }
}

export default Post;
