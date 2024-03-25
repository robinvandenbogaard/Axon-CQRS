package nl.robinthedev.app.post.axon;

public interface ProfanityService {
  ProfanityEvaluation calculateScore(String comment);
}
