package nl.robinthedev.app.api.messaging.event;

import nl.robinthedev.app.api.model.PostId;
import nl.robinthedev.app.api.model.Post;

public record PostCreated(PostId postId, Post post) {
}
