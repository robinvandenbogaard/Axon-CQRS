package nl.robinthedev.app.post.projection.database;

import java.util.List;
import nl.robinthedev.app.post.projection.core.PostService;
import nl.robinthedev.app.post.projection.core.model.CommentForPost;
import nl.robinthedev.app.post.projection.core.model.NewPost;
import nl.robinthedev.app.post.projection.core.model.Post;
import nl.robinthedev.app.post.projection.core.model.UpdatedComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class JpaPostService implements PostService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  JpaPostService(PostRepository postRepository, CommentRepository commentRepository) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
  }

  @Override
  @Transactional
  public List<? extends Post> getAllPosts() {
    return postRepository.findAll();
  }

  @Override
  @Transactional
  public void createPost(NewPost post) {
    JpaPost entity = new JpaPost(post.id(), post.title(), post.text());
    postRepository.save(entity);
  }

  @Override
  @Transactional
  public void addCommentToPost(CommentForPost commentToAdd) {
    JpaPost post = postRepository.findByPublicId(commentToAdd.postId()).orElse(null);
    if (post != null) {
      JpaComment entity = new JpaComment(commentToAdd.commentId(), commentToAdd.text());
      post.addComment(entity);
      postRepository.save(post);
    }
  }

  @Override
  @Transactional
  public void updateCommentText(UpdatedComment updatedComment) {
    JpaComment comment = commentRepository.findByPublicId(updatedComment.commentId()).orElse(null);
    if (comment != null) {
      comment.setText(updatedComment.newText());
      commentRepository.flush();
    }
  }
}
