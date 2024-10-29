package cl.ipchile.medicalmanagement.service;

import cl.ipchile.medicalmanagement.model.Patient;
import cl.ipchile.medicalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getOrCreatePatient(String name, String dni) {
        return patientRepository.findByDni(dni)
                .map(patient -> {
                    // Actualizar nombre si es diferente
                    if (!patient.getName().equals(name)) {
                        patient.setName(name);
                        return patientRepository.save(patient);
                    }
                    return patient;
                })
                .orElseGet(() -> {
                    Patient newPatient = new Patient();
                    newPatient.setName(name);
                    newPatient.setDni(dni);
                    return patientRepository.save(newPatient);
                });
    }
}