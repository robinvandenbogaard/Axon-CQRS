package nl.robinthedev.app.post.command;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class RandomUuidGenerator implements UuidGenerator {
  @Override
  public UUID generateId() {
    return UUID.randomUUID();
  }
}
