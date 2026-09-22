package br.com.knw.devshowcase.repository;
import br.com.knw.devshowcase.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProfileRepository extends JpaRepository<Profile, Long> {}
