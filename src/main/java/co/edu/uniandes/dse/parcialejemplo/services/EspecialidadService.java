package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.*;
import co.edu.uniandes.dse.parcialejemplo.repositories.*;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class EspecialidadService {
    
    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity createEspecialidad (EspecialidadEntity especialidadEntity)
            throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación de la especialidad");
        if (especialidadEntity.getDescripcion().length() < 10)
            throw new IllegalOperationException("descripción no es válida");  
        return especialidadRepository.save(especialidadEntity);
    }
}
