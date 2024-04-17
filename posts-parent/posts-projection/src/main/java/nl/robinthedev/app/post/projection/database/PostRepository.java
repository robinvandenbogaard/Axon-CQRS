package nl.robinthedev.app.post.projection.database;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<JpaPost, Long> {

  Optional<JpaPost> findByPublicId(UUID uuid);
}
