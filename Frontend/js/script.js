// Variables globales
const API_URL = "http://localhost:8080/api";
let editingId = null;

// Referencias a elementos del DOM
const form = document.getElementById("medicalAttentionForm");
const formSection = document.getElementById("formSection");
const listSection = document.getElementById("listSection");
const attentionsTable = document.getElementById("attentionsTable");
const doctorSelect = document.querySelector('select[name="doctorId"]');
const doctorSpecialty = document.getElementById("doctorSpecialty");

// Event Listeners
document
  .getElementById("newAttentionLink")
  .addEventListener("click", showFormSection);
document
  .getElementById("listAttentionsLink")
  .addEventListener("click", showListSection);
form.addEventListener("submit", handleSubmit);
doctorSelect.addEventListener("change", handleDoctorChange);

// Funciones de navegación
function showFormSection(e) {
  e?.preventDefault();
  formSection.classList.remove("d-none");
  listSection.classList.add("d-none");
}

function showListSection(e) {
  e?.preventDefault();
  formSection.classList.add("d-none");
  listSection.classList.remove("d-none");
  loadAttentions();
}

// Cargar médicos al iniciar la aplicación
async function loadDoctors() {
  try {
    const response = await fetch(`${API_URL}/doctors`);
    const doctors = await response.json();

    doctorSelect.innerHTML = '<option value="">Seleccione un médico</option>';
    doctors.forEach((doctor) => {
      const option = document.createElement("option");
      option.value = doctor.id;
      option.textContent = doctor.name;
      option.dataset.specialty = doctor.specialty;
      doctorSelect.appendChild(option);
    });
  } catch (error) {
    console.error("Error al cargar médicos:", error);
    alert("Error al cargar la lista de médicos");
  }
}

// Manejar cambio de médico
function handleDoctorChange() {
  const selectedOption = doctorSelect.options[doctorSelect.selectedIndex];
  const specialty = selectedOption.dataset.specialty;
  doctorSpecialty.textContent = specialty ? `Especialidad: ${specialty}` : "";
}

// Validar RUT chileno
function validateRut(rut) {
  const rutRegex = /^\d{7,8}-[0-9kK]$/;
  return rutRegex.test(rut);
}

// Manejar envío del formulario
async function handleSubmit(e) {
  e.preventDefault();

  const formData = new FormData(form);
  const patientDni = formData.get("patientDni");

  if (!validateRut(patientDni)) {
    alert("El RUT debe tener el formato correcto (ej: 16243876-8)");
    return;
  }

  const data = {
    admissionDate: formData.get("admissionDate"),
    dischargeDate: formData.get("dischargeDate") || null,
    patientName: formData.get("patientName"),
    patientDni: patientDni,
    doctorId: parseInt(formData.get("doctorId")),
    activity: formData.get("activity"),
    diagnosis: formData.get("diagnosis"),
  };

  try {
    const url = editingId
      ? `${API_URL}/attentions/${editingId}`
      : `${API_URL}/attentions`;

    const method = editingId ? "PUT" : "POST";

    const response = await fetch(url, {
      method: method,
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    if (!response.ok) throw new Error("Error en la operación");

    alert(
      editingId
        ? "Atención actualizada con éxito"
        : "Atención registrada con éxito"
    );
    form.reset();
    editingId = null;
    showListSection();
  } catch (error) {
    console.error("Error:", error);
    alert("Error al procesar la solicitud");
  }
}

// Cargar lista de atenciones
async function loadAttentions() {
  try {
    const response = await fetch(`${API_URL}/attentions`);
    const attentions = await response.json();

    attentionsTable.innerHTML = "";
    attentions.forEach((attention) => {
      const row = document.createElement("tr");
      row.innerHTML = `
                <td>${formatDate(attention.admissionDate)}</td>
                <td>${attention.doctorName}</td>
                <td>${attention.patientName}</td>
                <td>${attention.diagnosis}</td>
                <td>${attention.activity}</td>
                <td>${
                  attention.dischargeDate
                    ? formatDate(attention.dischargeDate)
                    : "-"
                }</td>
                <td>
                    <button onclick="editAttention(${
                      attention.id
                    })" class="btn btn-sm btn-primary">Editar</button>
                    <button onclick="deleteAttention(${
                      attention.id
                    })" class="btn btn-sm btn-danger">Eliminar</button>
                </td>
            `;
      attentionsTable.appendChild(row);
    });
  } catch (error) {
    console.error("Error al cargar atenciones:", error);
    alert("Error al cargar la lista de atenciones");
  }
}

// Editar atención
async function editAttention(id) {
  try {
    const response = await fetch(`${API_URL}/attentions/${id}`);
    const attention = await response.json();

    editingId = id;
    form.elements["admissionDate"].value = attention.admissionDate;
    form.elements["dischargeDate"].value = attention.dischargeDate || "";
    form.elements["patientName"].value = attention.patientName;
    form.elements["patientDni"].value = attention.patientDni;
    form.elements["doctorId"].value = attention.doctorId;
    form.elements["activity"].value = attention.activity;
    form.elements["diagnosis"].value = attention.diagnosis;

    showFormSection();
  } catch (error) {
    console.error("Error al cargar la atención:", error);
    alert("Error al cargar los datos de la atención");
  }
}

// Eliminar atención
async function deleteAttention(id) {
  if (!confirm("¿Está seguro de eliminar esta atención?")) return;

  try {
    const response = await fetch(`${API_URL}/attentions/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) throw new Error("Error al eliminar");

    alert("Atención eliminada con éxito");
    loadAttentions();
  } catch (error) {
    console.error("Error al eliminar:", error);
    alert("Error al eliminar la atención");
  }
}

// Utilidad para formatear fechas
function formatDate(dateString) {
  if (!dateString) return "-";

  // Dividir la fecha en sus componentes
  const [year, month, day] = dateString.split("-");

  // Crear la fecha usando la zona horaria local
  return new Date(year, month - 1, day).toLocaleDateString("es-CL", {
    timeZone: "America/Santiago",
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  });
}

// Inicialización
document.addEventListener("DOMContentLoaded", () => {
  loadDoctors();
  showFormSection();
});
