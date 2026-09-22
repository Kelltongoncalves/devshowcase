package br.com.knw.devshowcase.repository;
import br.com.knw.devshowcase.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}
