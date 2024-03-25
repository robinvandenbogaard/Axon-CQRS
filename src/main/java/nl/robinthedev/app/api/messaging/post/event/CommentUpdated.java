package nl.robinthedev.app.api.messaging.post.event;

import nl.robinthedev.app.api.messaging.post.model.CommentId;

public record CommentUpdated(CommentId commentId, String oldText, String newText) {}
