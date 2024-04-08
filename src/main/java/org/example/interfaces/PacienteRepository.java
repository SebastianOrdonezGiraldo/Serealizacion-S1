package org.example.interfaces;


import org.example.domain.Paciente;

import java.util.List;

public interface PacienteRepository {
    List<Paciente> findAll();
    Paciente findByid(String cc);
    void save(Paciente paciente);
    void update(Paciente paciente);
    void delete(String cc);
}
