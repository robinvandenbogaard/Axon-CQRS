package nl.robinthedev.app.qs;

import java.util.List;

public record RPost(String title, String text, List<RComment> comments) {
}
