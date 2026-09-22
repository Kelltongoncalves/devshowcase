package br.com.knw.devshowcase.repository;
import br.com.knw.devshowcase.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProjectRepository extends JpaRepository<Project, Long> {}
