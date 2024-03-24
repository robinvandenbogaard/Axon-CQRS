package nl.robinthedev.app.api.messaging.command;

import nl.robinthedev.app.api.model.CommentId;

public record UpdateComment(CommentId commentId, String newText) {
}
