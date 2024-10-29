package cl.ipchile.medicalmanagement.controller;

import cl.ipchile.medicalmanagement.model.DoctorDTO;
import cl.ipchile.medicalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
