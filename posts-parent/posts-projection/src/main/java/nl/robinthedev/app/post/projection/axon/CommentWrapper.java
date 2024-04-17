package nl.robinthedev.app.post.projection.axon;

import java.util.UUID;
import nl.robinthedev.app.post.projection.core.model.CommentForPost;

record CommentWrapper(UUID postId, UUID commentId, String text) implements CommentForPost {}
