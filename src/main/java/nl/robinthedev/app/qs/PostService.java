package nl.robinthedev.app.qs;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PostService {

  private final PostRepository postRepository;

  PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<RPost> getAllPosts() {
    return postRepository.findAll().stream().map(this::toDto).toList();
  }

  private RPost toDto(Post entity) {
    return new RPost(entity.getTitle(), entity.getContent(), toDto(entity.getComments()));
  }

  private List<RComment> toDto(List<Comment> comments) {
    return comments.stream().map(this::toDto).toList();
  }

  private RComment toDto(Comment entity) {
    return new RComment(entity.getText());
  }

  public RPost getPostById(Long postId) {
    return postRepository.findById(postId).map(this::toDto).orElse(null);
  }

  public List<Comment> getCommentsForPost(Long postId) {
    Post post = postRepository.findById(postId).orElse(null);
    if (post != null) {
      return post.getComments();
    }
    return null;
  }
}