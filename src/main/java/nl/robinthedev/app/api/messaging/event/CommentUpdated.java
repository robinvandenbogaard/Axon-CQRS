package nl.robinthedev.app.api.messaging.event;

import nl.robinthedev.app.api.model.CommentId;

public record CommentUpdated(CommentId commentId, String oldText, String newText) {}
