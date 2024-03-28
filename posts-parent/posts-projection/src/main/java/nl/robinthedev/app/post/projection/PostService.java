package nl.robinthedev.app.post.projection;

import java.util.List;
import nl.robinthedev.app.api.messaging.post.model.CommentId;
import nl.robinthedev.app.api.messaging.post.model.PostId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PostService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  PostService(PostRepository postRepository, CommentRepository commentRepository) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
  }

  @Transactional
  public List<RPost> getAllPosts() {
    return postRepository.findAll().stream().map(this::toDto).toList();
  }

  private RPost toDto(JpaPost entity) {
    return new RPost(
        entity.getPublicId(), entity.getTitle(), entity.getContent(), toDto(entity.getComments()));
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

  @Transactional
  public void updateCommentText(CommentId commentId, String newText) {
    JpaComment comment = commentRepository.findByPublicId(commentId.commentId()).orElse(null);
    if (comment != null) {
      comment.setText(newText);
      commentRepository.flush();
    }
  }
}
