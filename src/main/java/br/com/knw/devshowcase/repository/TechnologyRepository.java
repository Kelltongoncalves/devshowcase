package br.com.knw.devshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.knw.devshowcase.model.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
	
}