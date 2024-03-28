package nl.robinthedev.app.post.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.ArrayList;
import java.util.List;
import nl.robinthedev.app.api.messaging.post.command.AddComment;
import nl.robinthedev.app.api.messaging.post.command.CreatePost;
import nl.robinthedev.app.api.messaging.post.event.CommentAdded;
import nl.robinthedev.app.api.messaging.post.event.PostCreated;
import nl.robinthedev.app.api.messaging.post.model.Comment;
import nl.robinthedev.app.api.messaging.post.model.Post;
import nl.robinthedev.app.api.messaging.post.model.PostId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.modelling.command.ForwardMatchingInstances;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
class PostAggregate {

  @AggregateIdentifier PostId postId;

  @AggregateMember(eventForwardingMode = ForwardMatchingInstances.class)
  List<PostComment> comments = new ArrayList<>();

  public PostAggregate() {
    // required by Axon
  }

  @CommandHandler
  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  void handle(CreatePost createPost) {
    apply(new PostCreated(createPost.postId(), new Post(createPost.title(), createPost.content())));
  }

  @EventSourcingHandler
  void handle(PostCreated postCreated) {
    this.postId = postCreated.postId();
  }

  @CommandHandler
  void handle(AddComment addComment) {
    apply(new CommentAdded(addComment.postId(), new Comment(addComment.id(), addComment.text())));
  }

  @EventSourcingHandler
  void handle(CommentAdded commentAdded) {
    comments.add(new PostComment(commentAdded.comment()));
  }
}
