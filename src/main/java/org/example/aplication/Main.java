package org.example.aplication;

import org.example.aplication.service.CitaServiceImpl;
import org.example.aplication.service.PacienteServiceImpl;
import org.example.domain.Cita;
import org.example.domain.Paciente;
import org.example.infraestructure.repository.CitaRepositoryImpl;
import org.example.infraestructure.repository.PacienteRepositoryImpl;
import org.example.interfaces.CitaRepository;
import org.example.interfaces.CitaService;
import org.example.interfaces.PacienteRepository;
import org.example.interfaces.PacienteService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CitaService citaService;
    private static final PacienteService pacienteService;

    static {
        CitaRepository citaRepository = new CitaRepositoryImpl();
        citaService = new CitaServiceImpl(citaRepository);

        PacienteRepository pacienteRepository = new PacienteRepositoryImpl();
        pacienteService = new PacienteServiceImpl(pacienteRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Registrar paciente nuevo");
            System.out.println("2. Actualizar datos");
            System.out.println("3. registrar cita paciente existente");
            System.out.println("4. eliminar cita");
            System.out.println("5. mostrar pacientes");
            System.out.println("6. Mostrar citas del paciente");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarPaciente();
                    break;
                case 2:

                    actualizarPaciente();
                    break;
                case 3:

                    registrarCita();
                    break;
                case 4:

                    eliminarCita();
                    break;
                case 5:
                    listarPacientes();

                    break;
                case 6:
                    listarCitas();
                    break;
                case 7:
                    salir = true;
                    break;
                case 8:
                    listarTodasCitas();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void registrarPaciente() {

        System.out.print("Ingrese la identificacion del paciente: ");
        String cc = scanner.nextLine();
        System.out.print("Ingrese el nombre del paciente: ");
        String nombrePaciente = scanner.nextLine();
        System.out.print("Ingrese el apellido del paciente: ");
        String apellidoPaciente = scanner.nextLine();
        System.out.print("Ingrese la edad del paciente: ");
        short edad = scanner.nextShort();
        System.out.print("Ingrese el genero del paciente: ");
        String genero = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese la direccion del paciente: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese el telefono del paciente: ");
        String telefono = scanner.nextLine();

        Paciente pacientes = new Paciente(cc, nombrePaciente, apellidoPaciente, edad, genero, direccion, telefono);

        try {
            pacienteService.save(pacientes);
            System.out.println("Paciente creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarPaciente() {
        System.out.print("Ingrese el ID del paciente a actualizar: ");
        String cc = scanner.nextLine();

        Paciente pacientes = pacienteService.findByid(cc);
        if (pacientes == null) {
            System.out.println("No se encontró el paciente con cc " + cc);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del paciente (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            pacientes.setNombre(nombre);
        }
        System.out.print("Ingrese el nuevo apellido del paciente (dejar en blanco para no cambiar): ");
        String apellido = scanner.nextLine();
        if (!apellido.isEmpty()) {
            pacientes.setApellido(apellido);
        }

        System.out.print("Ingrese el genero del paciente (dejar en blanco para no cambiar): ");
        String genero = scanner.nextLine();
        if (!genero.isEmpty()) {
            pacientes.setGenero(genero);
        }

        System.out.print("Ingrese la nueva direccion del paciente (dejar en blanco para no cambiar): ");
        String direccion = scanner.nextLine();
        if (!direccion.isEmpty()) {
            pacientes.setDireccion(direccion);
        }

        System.out.print("Ingrese el nuevo numero de telefono del paciente (dejar en blanco para no cambiar): ");
        String telefono = scanner.nextLine();
        if (!telefono.isEmpty()) {
            pacientes.setTelefono(telefono);
        }

        try {
            pacienteService.update(pacientes);
            System.out.println("Paciente actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registrarCita() {

        List<Cita> citas = citaService.findAll();
        int counter = citas.size() + 1;

        System.out.print("Ingrese la identificacion del paciente para crear la cita: ");
        String cc = scanner.nextLine();
        Paciente paciente = pacienteService.findByid(cc);
        if (paciente == null) {
            System.out.println("No existe el usuario, debe estar registrado para crear la cita");
        } else {
            System.out.println("Ususario encontrado");
            System.out.println("Ingrese la hora de la cita:");
            String hora = scanner.nextLine();
            System.out.println("Ingrese la fecha de la cita:");
            String fecha = scanner.nextLine();
            System.out.println("Ingrese motivo de la cita:");
            String motivo = scanner.nextLine();
            citaService.save(new Cita(counter, paciente, hora, fecha, motivo));
            System.out.println("Registrado con exito");
        }
    }

    private static void eliminarCita() {
        System.out.print("Ingrese el ID de la cita a eliminar: ");
        int id = scanner.nextInt();

        Cita citas = citaService.findByid(id);
        if (citas == null) {
            System.out.println("No se encontró la cita con ID " + id);
            return;
        }

        citaService.delete(id);
        System.out.println("Producto eliminado exitosamente.");
    }

    private static void listarCitas() {
        List<Cita> citas = citaService.findAll();
        System.out.println("Identificacion del paciente: ");
        String cc = scanner.nextLine();
        boolean tieneCitas = false;

        for (Cita cita : citas) {
            if (cita.getPaciente().getCc().equals(cc)) {
                System.out.println(cita);
                tieneCitas = true;
            }
        }

        if (!tieneCitas) {
            System.out.println("Este paciente no tiene citas registradas");
        }
    }


    private static void listarPacientes() {

        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Listado de Pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println(paciente);
            }
        }
    }

    private static void listarTodasCitas() {

        List<Cita> citas = citaService.findAll();
        if (citas.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Listado de Pacientes:");
            for (Cita cita : citas) {
                System.out.println(cita);
            }
        }
    }


}