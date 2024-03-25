package nl.robinthedev.app.api.messaging.post.event;

import nl.robinthedev.app.api.messaging.post.model.Post;
import nl.robinthedev.app.api.messaging.post.model.PostId;

public record PostCreated(PostId postId, Post post) {}
