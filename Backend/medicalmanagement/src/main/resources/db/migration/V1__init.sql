CREATE TABLE doctors
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    specialty  VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE patients
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    dni        CHAR(10) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE medical_attentions
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    admission_date DATE   NOT NULL,
    doctor_id      BIGINT NOT NULL,
    patient_id     BIGINT NOT NULL,
    activity       TEXT   NOT NULL,
    diagnosis      TEXT   NOT NULL,
    discharge_date DATE,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES doctors (id),
    FOREIGN KEY (patient_id) REFERENCES patients (id)
);

-- Insertar de doctores en el sistema
INSERT INTO doctors (name, specialty)
VALUES ('Dr. Juan García', 'Cardiología'),
       ('Dra. María López', 'Pediatría'),
       ('Dr. Carlos Rodríguez', 'Traumatología'),
       ('Dra. Ana Martínez', 'Neurología'),
       ('Dr. Roberto Sánchez', 'Medicina General');

-- Registro de pacientes de ejemplo
INSERT INTO patients (dni, name)
VALUES ('16875988-6', 'Pedro Gómez'),
       ('15822977-4', 'Laura Torres'),
       ('18859992-K', 'Miguel Ángel Ruiz'),
       ('12828984-3', 'Carmen Flores'),
       ('16877985-2', 'José Luis Ramírez');

-- Registro de atenciones médicas de ejemplo
INSERT INTO medical_attentions (admission_date, doctor_id, patient_id, activity, diagnosis, discharge_date)
VALUES ('2024-01-15', 1, 1, 'Consulta cardiológica de rutina', 'Hipertensión arterial leve', '2024-01-15'),
       ('2024-02-01', 2, 2, 'Control pediátrico', 'Desarrollo normal, vacunación al día', '2024-02-01'),
       ('2024-02-15', 3, 3, 'Evaluación por dolor en rodilla', 'Esguince de rodilla grado I', '2024-02-20'),
       ('2024-03-01', 4, 4, 'Evaluación neurológica', 'Migraña crónica', '2024-03-01'),
       ('2024-03-10', 5, 5, 'Consulta por síntomas gripales', 'Infección respiratoria aguda', '2024-03-12');
