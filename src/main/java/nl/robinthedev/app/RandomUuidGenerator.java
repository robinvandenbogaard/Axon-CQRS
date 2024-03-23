package nl.robinthedev.app;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class RandomUuidGenerator implements UuidGenerator {
  @Override
  public UUID generateId() {
    return UUID.randomUUID();
  }
}
