package nl.robinthedev.app.api.messaging.post.command;

import nl.robinthedev.app.api.messaging.post.model.PostId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreatePost(@TargetAggregateIdentifier PostId postId, String title, String content) {}
