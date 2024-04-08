package org.example.aplication.service;

import org.example.domain.Paciente;
import org.example.infraestructure.repository.PacienteRepositoryImpl;
import org.example.interfaces.PacienteRepository;
import org.example.interfaces.PacienteService;

import java.util.List;

public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente findByid(String cc) {
        return pacienteRepository.findByid(cc);
    }

    @Override
    public void save(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void update(Paciente paciente) {
        pacienteRepository.update(paciente);
    }

    @Override
    public void delete(String cc) {
        pacienteRepository.delete(cc);
    }
}
