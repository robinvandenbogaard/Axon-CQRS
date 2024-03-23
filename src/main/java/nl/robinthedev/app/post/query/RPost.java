package nl.robinthedev.app.post.query;

import java.util.List;
import java.util.UUID;

record RPost(UUID id, String title, String text, List<RComment> comments) {
}
