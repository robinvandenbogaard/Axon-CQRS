package nl.robinthedev.app.api.messaging.event;

import nl.robinthedev.app.api.model.CommentId;

public record CommentRejected(CommentId commentId, String text, String reason) {}
