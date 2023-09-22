package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToMany;     

/*
 * Clase que representa un medico en la persistencia
 * 
 * María Paula Estupiñan – Parcial 1
 */

@Setter
@Getter
@Entity

public class MedicoEntity extends BaseEntity{
         
    private String nombre;
    private String apellido;
    private String registro_medico;

    @PodamExclude
    @ManyToMany
    private List<EspecialidadEntity> especialidad = new ArrayList<>();

}
