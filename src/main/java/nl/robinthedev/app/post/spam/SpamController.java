package nl.robinthedev.app.post.spam;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import java.util.UUID;
import nl.robinthedev.app.api.messaging.post.command.AddComment;
import nl.robinthedev.app.api.messaging.post.command.CreatePost;
import nl.robinthedev.app.api.messaging.post.command.UpdateComment;
import nl.robinthedev.app.api.messaging.post.model.CommentId;
import nl.robinthedev.app.api.messaging.post.model.PostId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class SpamController {

  private final CommandGateway gateway;
  private final Lorem lorem;

  SpamController(CommandGateway gateway) {
    this.gateway = gateway;
    this.lorem = LoremIpsum.getInstance();
  }

  @GetMapping("/spam")
  public void spammy() throws InterruptedException {
    PostId postId = new PostId(getUuid());
    gateway.sendAndWait(new CreatePost(postId, lorem.getTitle(4, 8), lorem.getParagraphs(2, 4)));
    addComment(postId, 1.0d);
    addComment(postId);
    addComment(postId);
    addComment(postId);
  }

  private void addComment(PostId postId) throws InterruptedException {
    addComment(postId, 0.5d);
  }

  private void addComment(PostId postId, double v) throws InterruptedException {
    CommentId commentId = new CommentId(getUuid());
    if (Math.random() < v) {
      gateway.sendAndWait(new AddComment(postId, commentId, lorem.getParagraphs(2, 4)));
      editComment(postId, commentId);
      editComment(postId, commentId);
      editComment(postId, commentId);
      editComment(postId, commentId);
      editComment(postId, commentId);
      editComment(postId, commentId);
    }
  }

  private void editComment(PostId postId, CommentId commentId) throws InterruptedException {
    if (Math.random() < 0.5d) {
      gateway.sendAndWait(new UpdateComment(postId, commentId, lorem.getParagraphs(2, 4)));
    }
  }

  private UUID getUuid() {
    return UUID.randomUUID();
  }
}
