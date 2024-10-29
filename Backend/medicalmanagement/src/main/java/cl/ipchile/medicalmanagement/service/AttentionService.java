package cl.ipchile.medicalmanagement.service;

import cl.ipchile.medicalmanagement.model.*;
import cl.ipchile.medicalmanagement.repository.AttentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttentionService {

    private final AttentionRepository attentionRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    public AttentionService(
            AttentionRepository attentionRepository,
            DoctorService doctorService,
            PatientService patientService) {
        this.attentionRepository = attentionRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public List<AttentionDTO> getAllAttentions() {
        return attentionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AttentionDTO getAttention(Long id) {
        MedicalAttention attention = attentionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Atención médica no encontrada"));
        return convertToDTO(attention);
    }

    public AttentionDTO createAttention(AttentionDTO attentionDTO) {
        MedicalAttention attention = new MedicalAttention();
        return saveAttention(attention, attentionDTO);
    }

    public AttentionDTO updateAttention(Long id, AttentionDTO attentionDTO) {
        MedicalAttention attention = attentionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Atención médica no encontrada"));
        return saveAttention(attention, attentionDTO);
    }

    public void deleteAttention(Long id) {
        if (!attentionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Atención médica no encontrada");
        }
        attentionRepository.deleteById(id);
    }

    private AttentionDTO saveAttention(MedicalAttention attention, AttentionDTO dto) {
        // Obtener o crear paciente
        Patient patient = patientService.getOrCreatePatient(
                dto.patientName(),
                dto.patientDni()
        );

        // Obtener doctor
        Doctor doctor = doctorService.getDoctorById(dto.doctorId());

        // Actualizar la atención médica
        attention.setAdmissionDate(dto.admissionDate());
        attention.setDischargeDate(dto.dischargeDate());
        attention.setPatient(patient);
        attention.setDoctor(doctor);
        attention.setActivity(dto.activity());
        attention.setDiagnosis(dto.diagnosis());

        // Guardar y convertir a DTO
        MedicalAttention savedAttention = attentionRepository.save(attention);
        return convertToDTO(savedAttention);
    }

    private AttentionDTO convertToDTO(MedicalAttention attention) {
        return new AttentionDTO(
                attention.getId(),
                attention.getAdmissionDate(),
                attention.getDischargeDate(),
                attention.getPatient().getName(),
                attention.getPatient().getDni(),
                attention.getDoctor().getId(),
                attention.getDoctor().getName(),
                attention.getActivity(),
                attention.getDiagnosis()
        );
    }
}