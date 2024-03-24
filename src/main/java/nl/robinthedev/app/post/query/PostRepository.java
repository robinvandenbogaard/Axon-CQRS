package nl.robinthedev.app.post.query;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<JpaPost, Long> {

  Optional<JpaPost> findByPublicId(UUID uuid);
}
