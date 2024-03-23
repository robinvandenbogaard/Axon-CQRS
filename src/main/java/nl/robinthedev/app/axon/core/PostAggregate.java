package nl.robinthedev.app.axon.core;

import nl.robinthedev.app.api.messaging.command.CreatePost;
import nl.robinthedev.app.api.messaging.event.PostCreated;
import nl.robinthedev.app.api.model.Post;
import nl.robinthedev.app.api.model.PostId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
class PostAggregate {

  @AggregateIdentifier
  PostId postId;

  public PostAggregate() {
    //required by Axon
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
}
