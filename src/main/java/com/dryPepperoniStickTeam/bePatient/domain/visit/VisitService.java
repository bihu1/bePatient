package com.dryPepperoniStickTeam.bePatient.domain.visit;

import com.dryPepperoniStickTeam.bePatient.domain.doctor.Doctor;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.DoctorRepository;
import com.dryPepperoniStickTeam.bePatient.domain.patient.PatientRepository;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import com.dryPepperoniStickTeam.bePatient.domain.visit.http.model.ReservedVisitView;
import com.dryPepperoniStickTeam.bePatient.domain.visit.http.model.VisitView;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VisitService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final MapperFacade mapper;

    public List<VisitView> getAllAvailableDoctorsVisits(long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(RuntimeException::new);
        List<Visit> visits = visitRepository.findByDoctorAndPatient(doctor, null);
        return mapper.mapAsList(visits, VisitView.class);
    }

    public List<ReservedVisitView> getAllPatientsVisits(long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(RuntimeException::new);
        List<Visit> visits = visitRepository.findByPatient(patient);
        return mapper.mapAsList(visits, ReservedVisitView.class);
    }

    public void reserveVisitByPatient(long doctorId, long visitId, long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(RuntimeException::new);
        Visit visit = visitRepository.findByIdAndDoctor(visitId, doctor).orElseThrow(RuntimeException::new);
        Patient patient = patientRepository.findById(patientId).orElseThrow(RuntimeException::new);
        visit.setPatient(patient);
        patient.getVisits().add(visit);
        //fixme check if save is needed
    }
}
