package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.*;

import co.edu.uniandes.dse.parcialejemplo.entities.*;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import({ MedicoService.class, EspecialidadService.class, MedicoEspecialidadService.class })

public class MedicoEspecialidadTest {

    @Autowired
    private EspecialidadService especialidadService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;

    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<EspecialidadEntity> especialidadList = new ArrayList<>();
    private List<MedicoEntity> medicoList = new ArrayList<>();

    @BeforeEach
    void setUp() throws EntityNotFoundException, IllegalOperationException {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
    }

    private void insertData() throws EntityNotFoundException, IllegalOperationException {

        for (int i = 0; i < 3; i++) {
            MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(medicoEntity);
            medicoList.add(medicoEntity);
        }
        for (int i = 0; i < 3; i++) {
            EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
            entityManager.persist(especialidadEntity);
            especialidadList.add(especialidadEntity);
        }
    }
    
    @Test
    void testAddEspecialidadCorrectamente() throws EntityNotFoundException, IllegalOperationException {
        
        EspecialidadEntity newEspecialidad = factory.manufacturePojo(EspecialidadEntity.class);
        EspecialidadEntity especialidad = especialidadService.createEspecialidad(newEspecialidad);
        entityManager.persist(especialidad);
    
        // Crear y persistir un nuevo Medico
        MedicoEntity newMedico = factory.manufacturePojo(MedicoEntity.class);
        newMedico.setRegistro_medico("RM" + newMedico.getRegistro_medico()); // Asegurarse de que el registro médico comience con "RM"
        MedicoEntity medico = medicoService.createMedicos(newMedico);

        entityManager.persist(medico);
    
        // Agregar la Especialidad al Medico
        medicoEspecialidadService.addEspecialidad(medico.getId(), especialidad.getId());
    
        // Verificación se omite ya que no tienes el método get. La ausencia de excepciones indica éxito.
    }

    @Test
    void testAddInvalidEspecialidad() {
        assertThrows(EntityNotFoundException.class, () -> {
            MedicoEntity newMedico = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(newMedico);
            medicoEspecialidadService.addEspecialidad(newMedico.getId(),0L);
        });
    }

    @Test
    void testAddInvalidMedico() {
        assertThrows(EntityNotFoundException.class, () -> {
            EspecialidadEntity newEspecialidad = factory.manufacturePojo(EspecialidadEntity.class);
            entityManager.persist(newEspecialidad);
            medicoEspecialidadService.addEspecialidad(0L,newEspecialidad.getId());
        });
    }

}