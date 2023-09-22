package co.edu.uniandes.dse.parcialejemplo.repositories;

//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.parcialejemplo.entities.*;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> { 
    Optional<MedicoEntity> findById(Long idLong);
}
