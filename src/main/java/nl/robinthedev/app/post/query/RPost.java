package nl.robinthedev.app.post.query;

import java.util.List;

public record RPost(String title, String text, List<RComment> comments) {
}
