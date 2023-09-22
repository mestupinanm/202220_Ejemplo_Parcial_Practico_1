package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.*;
import javax.transaction.Transactional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcialejemplo.entities.*;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class MedicoEspecialidadService {
    
    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
	public EspecialidadEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
		
        log.info("Inicia proceso de agregarle una especialidad al medico con id = {0}", medicoId);
		
		Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
		if(medicoEntity.isEmpty())
			throw new EntityNotFoundException("No se encontro el medico" ); 
		
		Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);
		if(especialidadEntity.isEmpty())
			throw new EntityNotFoundException("No se encontro la especialidad");
		
		medicoEntity.get().getEspecialidad().add(especialidadEntity.get());
		log.info("Termina proceso de agregarle una especialidad al medico id = {0}", medicoId);
		return especialidadEntity.get();
	}
}
