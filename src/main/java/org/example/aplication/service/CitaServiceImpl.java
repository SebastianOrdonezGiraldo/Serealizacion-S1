package org.example.aplication.service;

import org.example.domain.Cita;
import org.example.interfaces.CitaRepository;
import org.example.interfaces.CitaService;

import java.util.List;

public class CitaServiceImpl implements CitaService {
    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    @Override
    public Cita findByid(int id) {
        return citaRepository.findByid(id);
    }

    @Override
    public void save(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public void update(Cita cita) {
        citaRepository.update(cita);
    }

    @Override
    public void delete(int id) {
        citaRepository.delete(id);
    }

}
