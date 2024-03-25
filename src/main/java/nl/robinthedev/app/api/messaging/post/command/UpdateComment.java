package nl.robinthedev.app.api.messaging.post.command;

import nl.robinthedev.app.api.messaging.post.model.CommentId;
import nl.robinthedev.app.api.messaging.post.model.PostId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record UpdateComment(
    @TargetAggregateIdentifier PostId postId, CommentId commentId, String newText) {}
