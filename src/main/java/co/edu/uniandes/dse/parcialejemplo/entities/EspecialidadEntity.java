package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import uk.co.jemos.podam.common.PodamExclude;

/*
 * Clase que representa una especialidad en la persistencia
 * 
 * María Paula Estupiñan – Parcial 1
 */

@Setter
@Getter
@Entity

public class EspecialidadEntity extends BaseEntity{
    
    private String nombre;
    private String descripcion;

    @PodamExclude
    @ManyToMany (mappedBy = "medico")
    private List<MedicoEntity> medico = new ArrayList<>();

}
