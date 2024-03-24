package nl.robinthedev.app.api.messaging.command;

import nl.robinthedev.app.api.model.CommentId;
import nl.robinthedev.app.api.model.PostId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddComment(@TargetAggregateIdentifier PostId postId, CommentId id, String text) {}
