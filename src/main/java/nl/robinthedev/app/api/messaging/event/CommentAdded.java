package nl.robinthedev.app.api.messaging.event;

import nl.robinthedev.app.api.model.CommentId;
import nl.robinthedev.app.api.model.PostId;

public record CommentAdded(PostId postId, CommentId id, String text) {

}
