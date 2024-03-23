package nl.robinthedev.app.post.command;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class RandomUuidGenerator implements UuidGenerator {
  @Override
  public UUID generateId() {
    return UUID.randomUUID();
  }
}
