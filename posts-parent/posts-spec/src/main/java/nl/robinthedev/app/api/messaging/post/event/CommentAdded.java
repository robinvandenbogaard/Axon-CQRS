package nl.robinthedev.app.api.messaging.post.event;

import nl.robinthedev.app.api.messaging.post.model.Comment;
import nl.robinthedev.app.api.messaging.post.model.PostId;

public record CommentAdded(PostId postId, Comment comment) {}
