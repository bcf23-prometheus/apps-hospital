package com.example.hospital.service;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.model.Appointment;
import com.example.hospital.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public void scheduleAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = Appointment.builder()
                .hospitalName(appointmentDto.getHospitalName())
                .appointmentDate(appointmentDto.getAppointmentDate())
                .nid(appointmentDto.getNid())
                .vaccineName(appointmentDto.getVaccineName())
                .isVaccinated(false)
                .build();
        log.info("Appointment created successfully at hospital", appointment);

        appointmentRepository.save(appointment);
    }

}
