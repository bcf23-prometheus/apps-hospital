package com.example.hospital.Controller;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.model.Appointment;
import com.example.hospital.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class Scheduler {

    private final AppointmentService appointmentService;

    @PostMapping("/schedule-appointment")
    @ResponseStatus(HttpStatus.CREATED)
    public String scheduleAppointment(AppointmentDto appointmentDto) {
        appointmentService.scheduleAppointment(appointmentDto);
        return "Appointment scheduled successfully";
    }

}
