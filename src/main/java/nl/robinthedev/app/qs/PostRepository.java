package nl.robinthedev.app.qs;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<Post, Long> {

}
