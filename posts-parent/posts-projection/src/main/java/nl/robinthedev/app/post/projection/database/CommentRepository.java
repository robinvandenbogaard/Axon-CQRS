package nl.robinthedev.app.post.projection.database;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository<JpaComment, Long> {

  Optional<JpaComment> findByPublicId(UUID uuid);
}
