package nl.robinthedev.app.post.command;

import java.util.UUID;
import nl.robinthedev.app.api.messaging.post.command.AddComment;
import nl.robinthedev.app.api.messaging.post.command.CreatePost;
import nl.robinthedev.app.api.messaging.post.command.UpdateComment;
import nl.robinthedev.app.api.messaging.post.model.CommentId;
import nl.robinthedev.app.api.messaging.post.model.PostId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class PostCommandController {

  private final CommandGateway gateway;
  private final UuidGenerator idGenerator;

  PostCommandController(CommandGateway gateway, UuidGenerator idGenerator) {
    this.gateway = gateway;
    this.idGenerator = idGenerator;
  }

  @PostMapping("/posts")
  public ResponseEntity<PostId> createPost(@RequestBody RNewPost post) {
    PostId id = new PostId(idGenerator.generateId());
    gateway.send(new CreatePost(id, post.title(), post.content()));
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @PostMapping("/posts/{postId}/addcomment")
  public ResponseEntity<CommentId> addComment(
      @PathVariable("postId") UUID postId, @RequestBody RNewComment comment) {
    CommentId id = new CommentId(idGenerator.generateId());
    gateway.send(new AddComment(new PostId(postId), id, comment.text()));
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @PutMapping("/posts/{postId}/comments/{commentId}/update")
  public ResponseEntity<CommentId> updateComment(
      @PathVariable("postId") UUID postId,
      @PathVariable("commentId") UUID commentUuid,
      @RequestBody RNewComment comment) {
    CommentId commentId = new CommentId(commentUuid);
    gateway.send(new UpdateComment(new PostId(postId), commentId, comment.text()));
    return new ResponseEntity<>(commentId, HttpStatus.OK);
  }
}
