package com.example.hospital.Controller;

import com.example.hospital.dto.GiveVaccineDto;
import com.example.hospital.dto.HospitalRequest;
import com.example.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/create-hospital")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody HospitalRequest hospitalRequest) {
        hospitalService.createHospital(hospitalRequest);
        return "Hospital created successfully";
    }

    @PatchMapping("/update-hospital")
    @ResponseStatus(HttpStatus.OK)
    public String updateHospital(@RequestBody HospitalRequest hospitalRequest) {
        hospitalService.updateHospital(hospitalRequest);
        return "Hospital updated successfully";
    }

    @GetMapping("/get-all-available-vaccines/{name}") // http://localhost:1323/api/hospital/get-available-vaccines?name=Hospital1
    @ResponseStatus(HttpStatus.OK)
    public String getAvailableVaccines(@PathVariable String name) {
        return hospitalService.getAllAvailableVaccines(name);
    }

    @GetMapping("/get-available-vaccines")
    @ResponseStatus(HttpStatus.OK)
    public String getAvailableVaccines(@RequestParam String hospitalName, @RequestParam String vaccineName) {
        return hospitalService.getAvailableVaccines(hospitalName, vaccineName);
    }

    @PostMapping("/give-vaccine")
    @ResponseStatus(HttpStatus.OK)
    public String giveVaccine(GiveVaccineDto giveVaccineDto) throws Exception {
        return hospitalService.giveVaccine(giveVaccineDto);
    }


//
//    @GetMapping("/get-all-products")
//    @ResponseStatus(HttpStatus.OK)
//    public List<HospitalResponse> getAllProducts(){
//        return hospitalService.getAllProducts();
//    }

}
