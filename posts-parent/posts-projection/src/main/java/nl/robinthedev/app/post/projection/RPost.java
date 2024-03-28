package nl.robinthedev.app.post.projection;

import java.util.List;
import java.util.UUID;

record RPost(UUID id, String title, String text, List<RComment> comments) {}
