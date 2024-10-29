package cl.ipchile.medicalmanagement.model;

import java.time.LocalDate;

public record AttentionDTO(
        Long id,
        LocalDate admissionDate,
        LocalDate dischargeDate,
        String patientName,
        String patientDni,
        Long doctorId,
        String doctorName,
        String activity,
        String diagnosis
) {}