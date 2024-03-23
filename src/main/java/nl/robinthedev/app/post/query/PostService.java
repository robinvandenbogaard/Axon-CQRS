package nl.robinthedev.app.post.query;

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

  private RPost toDto(JpaPost entity) {
    return new RPost(entity.getTitle(), entity.getContent(), toDto(entity.getComments()));
  }

  private List<RComment> toDto(List<JpaComment> comments) {
    return comments.stream().map(this::toDto).toList();
  }

  private RComment toDto(JpaComment entity) {
    return new RComment(entity.getText());
  }

  public RPost getPostById(Long postId) {
    return postRepository.findById(postId).map(this::toDto).orElse(null);
  }

  public List<JpaComment> getCommentsForPost(Long postId) {
    JpaPost post = postRepository.findById(postId).orElse(null);
    if (post != null) {
      return post.getComments();
    }
    return null;
  }
}