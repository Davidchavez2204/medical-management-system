package cl.ipchile.medicalmanagement.repository;

import cl.ipchile.medicalmanagement.model.MedicalAttention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttentionRepository extends JpaRepository<MedicalAttention, Long> {
    List<MedicalAttention> findByPatientDni(String dni);
}