package nl.robinthedev.app.api.messaging.post.event;

import nl.robinthedev.app.api.messaging.post.model.CommentId;

public record CommentRejected(CommentId commentId, String text, String reason) {}
