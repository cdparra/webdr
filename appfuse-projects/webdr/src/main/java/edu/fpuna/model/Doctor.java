/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fpuna.model;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;

/**
 * Clase que representa a un doctor
 * @author ghuttemann
 */
@Entity
@Table(name="doctor")
public class Doctor extends User {
    
    private Date fechaNacimiento;
    private Set<Especialidad> especialidades = new HashSet<Especialidad>();
    private Set<HorarioAtencion> horarios = new HashSet<HorarioAtencion>();
    
    @Column(name="fechanac",nullable=false)
    @Temporal(TemporalType.DATE)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void agregarEspecialidad(Especialidad especialidad) {
        this.especialidades.add(especialidad);
    }
    
    public void eliminarEspecialidad(Especialidad especialidad) {
        this.especialidades.remove(especialidad);
    }

    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
    @JoinTable(
        name = "doctor_especialidad",
        joinColumns = { @JoinColumn( name="doctor_id") },
        inverseJoinColumns = @JoinColumn(name="especialidad_id")
    )
    public Set<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }
    
    @OneToMany(fetch=FetchType.EAGER)
    public Set<HorarioAtencion> getHorarios() {
        return horarios;
    }
    
    public void setHorarios(Set<HorarioAtencion> horarios) {
        this.horarios = horarios;
    }
}
