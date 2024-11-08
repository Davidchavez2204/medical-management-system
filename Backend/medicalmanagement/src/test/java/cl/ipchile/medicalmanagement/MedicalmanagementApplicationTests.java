package cl.ipchile.medicalmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Usa el perfil de prueba
class MedicalmanagementApplicationTests {

    @Test
    void contextLoads() {
    }
}
