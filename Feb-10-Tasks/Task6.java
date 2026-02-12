import java.util.*;
import java.util.stream.*;

class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int i, String n, String s) {
        id = i;
        name = n;
        specialization = s;
    }
}

class Patient {
    int id;
    String name;
    Doctor doctor;

    Patient(int i, String n, Doctor d) {
        id = i;
        name = n;
        doctor = d;
    }
}

class Hospital {
    List<Doctor> doctors = new ArrayList<>();
    List<Patient> patients = new ArrayList<>();

    void addDoctor(Doctor d) {
        doctors.add(d);
    }

    void addPatient(Patient p) {
        patients.add(p);
    }

    void showAppointments() {
        for (Patient p : patients)
            System.out.println(p.name + " -> Dr." + p.doctor.name + " (" + p.doctor.specialization + ")");
    }

    void discharge(int id) {
        patients.removeIf(p -> p.id == id);
    }

    void searchDoctor(String spec) {
        doctors.stream()
                .filter(d -> d.specialization.equalsIgnoreCase(spec))
                .forEach(d -> System.out.println("Dr." + d.name + " " + d.specialization));
    }
}

public class Task6 {
    public static void main(String[] args) {
        Hospital h = new Hospital();

        Doctor d1 = new Doctor(1, "Ali", "Cardiology");
        Doctor d2 = new Doctor(2, "Sara", "Neurology");

        h.addDoctor(d1);
        h.addDoctor(d2);

        h.addPatient(new Patient(1, "John", d1));
        h.addPatient(new Patient(2, "Mary", d2));

        System.out.println("Appointments:");
        h.showAppointments();

        System.out.println("Search Cardiology:");
        h.searchDoctor("Cardiology");

        h.discharge(1);

        System.out.println("After Discharge:");
        h.showAppointments();
    }
}
