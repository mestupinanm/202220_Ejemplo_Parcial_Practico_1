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
@Import(MedicoService.class)

public class MedicoServiceTest {

    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<MedicoEntity> medicoList = new ArrayList<>();

    @BeforeEach
    void setUp() throws EntityNotFoundException, IllegalOperationException {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity");
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(medicoEntity);
            medicoList.add(medicoEntity);
        }
    }

    @Test
    void testcreateEspecialidad() throws EntityNotFoundException, IllegalOperationException {

        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        newEntity.setNombre("Pedro");
        newEntity.setApellido("Perez");
        newEntity.setRegistro_medico("RM1234");
        MedicoEntity result = medicoService.createMedicos(newEntity);
        assertNotNull(result);

        MedicoEntity entity = entityManager.find(MedicoEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getApellido(), entity.getApellido());
        assertEquals(newEntity.getRegistro_medico(), entity.getRegistro_medico());
    }

    @Test
    void testcreateEspecialidadInvalida() {
        assertThrows(IllegalOperationException.class, () -> {
            MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
            newEntity.setRegistro_medico("V1234");
            medicoService.createMedicos(newEntity);
        });
    }
}
