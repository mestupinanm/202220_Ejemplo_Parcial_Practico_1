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

public class MedicoService {
    
    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public MedicoEntity createMedicos (MedicoEntity medicoEntity)
            throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación de un médico");
        if (!medicoEntity.getRegistro_medico().startsWith("RM"))
            throw new IllegalOperationException("registro médico no es válido");  
        return medicoRepository.save(medicoEntity);
    }
}