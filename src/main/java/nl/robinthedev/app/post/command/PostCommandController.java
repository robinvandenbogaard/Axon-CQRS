package nl.robinthedev.app.post.command;

import nl.robinthedev.app.api.messaging.command.AddComment;
import nl.robinthedev.app.api.messaging.command.CreatePost;
import nl.robinthedev.app.api.model.CommentId;
import nl.robinthedev.app.api.model.PostId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
class PostCommandController {

  private final CommandGateway gateway;

  PostCommandController(CommandGateway gateway) {
    this.gateway = gateway;
  }

  @PostMapping("/posts")
  public ResponseEntity<PostId> createPost(@RequestBody RNewPost post) {
    PostId id = new PostId(UUID.randomUUID());
    gateway.send(new CreatePost(id, post.title(), post.content()));
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @PostMapping("/posts/{postId}")
  public ResponseEntity<CommentId> createComment(@PathVariable("postId") UUID postId, @RequestBody RNewComment comment) {
    CommentId id = new CommentId(UUID.randomUUID());
    gateway.send(new AddComment(new PostId(postId), id, comment.text()));
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }
}

