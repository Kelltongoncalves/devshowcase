package br.com.knw.devshowcase.repository;

import br.com.knw.devshowcase.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findDistinctByTechnologies_NameIgnoreCase(String technology, Pageable pageable);
}
