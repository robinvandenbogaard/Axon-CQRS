package nl.robinthedev.app.post.aggregate;

public interface ProfanityService {
  ProfanityEvaluation calculateScore(String comment);
}
