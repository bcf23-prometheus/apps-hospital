package com.example.hospital.service;

import com.example.hospital.dto.GiveVaccineDto;
import com.example.hospital.dto.HospitalRequest;
import com.example.hospital.model.Appointment;
import com.example.hospital.model.Doses;
import com.example.hospital.model.Hospital;
import com.example.hospital.model.Vaccined;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.repository.HospitalRepository;
import com.example.hospital.repository.VaccinedRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;
    private final VaccinedRepo vaccinedRepo;
    public void createHospital(HospitalRequest hospitalRequest) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalRequest.getName());
        hospital.setArea(hospitalRequest.getArea());
        hospital.setCapacity_per_day(hospitalRequest.getCapacity_per_day());

        List<Doses> doses = hospitalRequest.getDoses()
                .stream()
                .map(this::mapToDto)
                .toList();

        System.out.println("Doses that are in stock: " + doses);
        hospital.setDoses(doses);

        log.info("Hospital created: {}", hospital.getName());

        hospitalRepository.save(hospital);

    }

    private Doses mapToDto(Doses dosesRequest) {
        System.out.println("doses request: " + dosesRequest);
        Doses doses = new Doses();
        doses.setDose_name(dosesRequest.getDose_name());
        doses.setDose_quantity(dosesRequest.getDose_quantity());
        doses.setDose_distributor(dosesRequest.getDose_distributor());
        return doses;
    }

    public void updateHospital(HospitalRequest hospitalRequest) {
        Hospital exisitingHospital = hospitalRepository.findById(hospitalRequest.getId().toString()).orElseThrow(() -> new RuntimeException("Hospital not found"));
        BeanUtils.copyProperties(hospitalRequest, exisitingHospital, "id");

        hospitalRepository.save(exisitingHospital);

    }

    public String getAllAvailableVaccines(String hospitalName) {
        int totalVaccines = 0;
        Optional<Hospital> hospital = hospitalRepository.findByName(hospitalName);
        if (hospital.isEmpty()) {
            return "Hospital not found";
        }
        for (Doses doses : hospital.get().getDoses()) {
            totalVaccines += doses.getDose_quantity();
        }
        return "Total vaccines available in " + hospitalName + " is " + totalVaccines;
    }

    public String getAvailableVaccines(String hospitalName, String vaccineName) {
        int totalVaccines = 0;
        Optional<Hospital> hospital = hospitalRepository.findByName(hospitalName);
        if (hospital.isEmpty()) {
            return "Hospital not found";
        }
        for (Doses doses : hospital.get().getDoses()) {
            if (doses.getDose_name().equals(vaccineName)) {
                totalVaccines += doses.getDose_quantity();
            }
        }
        return "Total vaccines available in " + hospitalName + " is " + totalVaccines;
    }

    public String giveVaccine(GiveVaccineDto giveVaccineDto) throws Exception {
        Vaccined vaccined = new Vaccined();
        Optional<Appointment> appointment = appointmentRepository.findByNid(giveVaccineDto.getNid());
        if (appointment.isEmpty()) {
            throw new Exception("Appointment not found");
        }
        if (appointment.get().isVaccinated()) {
            throw new Exception("Already vaccinated");
        }
        else{
            appointment.get().setVaccinated(true);
        }

        Optional<Hospital> hospital = hospitalRepository.findByName(giveVaccineDto.getHospitalName());
        if (hospital.isEmpty()) {
            throw new Exception("Hospital not found");
        }
        for (Doses doses : hospital.get().getDoses()) {
            if (doses.getDose_name().equals(giveVaccineDto.getVaccineName())) {
                if (doses.getDose_quantity() > 0) {
                    if (vaccinedRepo.existsByNid(giveVaccineDto.getNid())) {
                        throw new Exception("Already vaccinated");
                    }
                    vaccined.setVaccineName(giveVaccineDto.getVaccineName());
                    vaccined.setNid(giveVaccineDto.getNid());
                    vaccined.setHid(hospital.get().getId());
                    vaccined.setLastVaccineDate(giveVaccineDto.getReceivedDate());
                    doses.setDose_quantity(doses.getDose_quantity() - 1);
                    appointmentRepository.save(appointment.get());
                    hospitalRepository.save(hospital.get());
                    return "Vaccine given successfully";
                }
            }
        }


        return "Vaccine not available";

    }


//    public List<HospitalResponse> getAllProducts() {
//        List<Hospital> vaccinated_users =  hospitalRepository.findAll();
//
//        return vaccinated_users.stream().map(this::mapToVaccinatedUsers).toList();
//    }

//    private List<HospitalResponse> mapToVaccinatedUsers(Hospital hospital) {
//        return HospitalResponse.builder()
//                .vaccinated_nid(hospital.getHid())
//                .build();
//    }
}
