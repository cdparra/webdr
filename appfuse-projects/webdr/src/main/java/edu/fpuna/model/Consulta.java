/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fpuna.model;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author ghuttemann
 */
@Entity
@Table(name="consulta")
public class Consulta extends BaseObject {

    private Long id;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;
    private MedidasPaciente medidasPaciente;
    private Notas notas;
    private Doctor doctor;
    private Paciente paciente;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="fecha",nullable=false)
    @Temporal(TemporalType.DATE)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name="hora_inicio",nullable=false)
    @Temporal(TemporalType.TIME)
    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    @Column(name="hora_fin",nullable=false)
    @Temporal(TemporalType.TIME)
    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    @Embedded
    public MedidasPaciente getMedidasPaciente() {
        return medidasPaciente;
    }

    public void setMedidasPaciente(MedidasPaciente medidasPaciente) {
        this.medidasPaciente = medidasPaciente;
    }
    
    @Embedded
    public Notas getNotas() {
        return notas;
    }

    public void setNotas(Notas notas) {
        this.notas = notas;
    }
    
    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="doctor_id",nullable=false)
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="paciente_id",nullable=false)
    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(id=" + this.id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Consulta)) {
            return false;
        }

        final Consulta consulta = (Consulta) o;

        if (this.getId().equals(consulta.getId())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        if (this.getId() == null)
            return 0;
        
        return this.getId().hashCode();
    }
}