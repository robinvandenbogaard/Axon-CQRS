package nl.robinthedev.app.post.query;

import nl.robinthedev.app.api.model.PostId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class PostService {

  private final PostRepository postRepository;

  PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Transactional
  public List<RPost> getAllPosts() {
    return postRepository.findAll().stream().map(this::toDto).toList();
  }

  private RPost toDto(JpaPost entity) {
    return new RPost(entity.getPublicId(), entity.getTitle(), entity.getContent(), toDto(entity.getComments()));
  }

  private List<RComment> toDto(List<JpaComment> comments) {
    return comments.stream().map(this::toDto).toList();
  }

  private RComment toDto(JpaComment entity) {
    return new RComment(entity.getPublicId(), entity.getText());
  }

  @Transactional
  public void createPost(JpaPost post) {
    postRepository.save(post);
  }

  @Transactional
  public void addCommentToPost(PostId postId, JpaComment comment) {
    JpaPost post = postRepository.findByPublicId(postId.postId()).orElse(null);
    if (post != null) {
      post.addComment(comment);
      postRepository.save(post);
    }
  }
}