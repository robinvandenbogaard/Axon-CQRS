package nl.robinthedev.app.api.messaging.event;

import nl.robinthedev.app.api.model.Comment;
import nl.robinthedev.app.api.model.PostId;

public record CommentAdded(PostId postId, Comment comment) {}
