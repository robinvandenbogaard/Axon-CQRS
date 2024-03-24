package nl.robinthedev.app.api.messaging.event;

import nl.robinthedev.app.api.model.Post;
import nl.robinthedev.app.api.model.PostId;

public record PostCreated(PostId postId, Post post) {}
