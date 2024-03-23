package nl.robinthedev.app.qs;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PostService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  PostService(PostRepository postRepository, CommentRepository commentRepository) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
  }

  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  public Post getPostById(Long postId) {
    return postRepository.findById(postId).orElse(null);
  }

  public List<Comment> getCommentsForPost(Long postId) {
    Post post = postRepository.findById(postId).orElse(null);
    if (post != null) {
      return post.getComments();
    }
    return null;
  }

  public Post createPost(Post post) {
    return postRepository.save(post);
  }

  public Comment addCommentToPost(Long postId, Comment comment) {
    Post post = postRepository.findById(postId).orElse(null);
    if (post != null) {
      comment.setPost(post);
      return commentRepository.save(comment);
    }
    return null;
  }
}