package nl.robinthedev.app.post.projection.core;

import java.util.List;
import nl.robinthedev.app.post.projection.core.model.CommentForPost;
import nl.robinthedev.app.post.projection.core.model.NewPost;
import nl.robinthedev.app.post.projection.core.model.Post;
import nl.robinthedev.app.post.projection.core.model.UpdatedComment;

public interface PostService {
  List<? extends Post> getAllPosts();

  void createPost(NewPost post);

  void addCommentToPost(CommentForPost commentToAdd);

  void updateCommentText(UpdatedComment updatedComment);
}
