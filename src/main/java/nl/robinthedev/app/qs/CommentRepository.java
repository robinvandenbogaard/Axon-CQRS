package nl.robinthedev.app.qs;

import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository<Comment, Long> {
}
