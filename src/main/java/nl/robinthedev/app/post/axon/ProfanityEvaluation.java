package nl.robinthedev.app.post.axon;

import static nl.robinthedev.app.post.axon.ProfanityEvaluation.*;

public sealed interface ProfanityEvaluation permits Acceptable, Unacceptable, FailureToEvaluate {

  record Acceptable() implements ProfanityEvaluation {}

  record Unacceptable() implements ProfanityEvaluation {}

  record FailureToEvaluate() implements ProfanityEvaluation {}
}
