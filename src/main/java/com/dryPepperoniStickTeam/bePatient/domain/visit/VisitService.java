package com.dryPepperoniStickTeam.bePatient.domain.visit;

import com.dryPepperoniStickTeam.bePatient.domain.disease.Disease;
import com.dryPepperoniStickTeam.bePatient.domain.disease.DiseaseRepository;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.Doctor;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.DoctorRepository;
import com.dryPepperoniStickTeam.bePatient.domain.patient.PatientRepository;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import com.dryPepperoniStickTeam.bePatient.domain.service.MedicalService;
import com.dryPepperoniStickTeam.bePatient.domain.service.MedicalServiceRepository;
import com.dryPepperoniStickTeam.bePatient.domain.visit.http.model.*;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.dryPepperoniStickTeam.bePatient.domain.visit.FilterVisitStatus.AVAILABLE;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VisitService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    private final MedicalServiceRepository medicalServiceRepository;
    private final DiseaseRepository diseaseRepository;

    private final VisitRepository visitRepository;
    private final MapperFacade mapper;

    public void addDoctorAvailableVisit(long doctorId, VisitDetails visitDetails){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(RuntimeException::new);
        Visit visit = mapper.map(visitDetails, Visit.class);
        visit.setStatus(VisitStatus.AVAILABLE);
        visit.setDoctor(doctor);
        doctor.addVisit(visit);
        visitRepository.save(visit);
    }

    public List<VisitView> getAllDoctorsVisitsByStatus(long doctorId, Optional<LocalDate> date, FilterVisitStatus patternVisitStatus){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(RuntimeException::new);
        List<Visit> visits = (patternVisitStatus == AVAILABLE ? visitRepository.findByDoctorAndPatient(doctor, null) : visitRepository.findByDoctor(doctor))
                .stream()
                .filter(v ->
                        date.map(d -> v.getDateFrom()
                                .toLocalDate()
                                .equals(d)
                        )
                        .orElse(true)
                )
                .collect(toList());
        return mapper.mapAsList(visits, VisitView.class);
    }

    public List<VisitView> getAllVisits(){
        List<Visit> visits = visitRepository.findAll();
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
        visit.setStatus(VisitStatus.RESERVED);
        patient.getVisits().add(visit);
        //fixme check if save is needed
    }

    public void assignDiseaseAndMedicalServices(long visitId ,DiseaseAndServicesIdsHolder diseaseAndServicesIdsHolder){
        List<Disease> diseases = diseaseRepository.findByIdIn(diseaseAndServicesIdsHolder.getDiseases());
        if(diseases.size() != diseaseAndServicesIdsHolder.getDiseases().size()){
            throw new RuntimeException();
        }
        List<MedicalService> medicalServices = medicalServiceRepository.findByIdIn(diseaseAndServicesIdsHolder.getServices());
        if(medicalServices.size() != diseaseAndServicesIdsHolder.getServices().size()){
            throw new RuntimeException();
        }

        Visit visit = visitRepository.findById(visitId).orElseThrow(RuntimeException::new);
        visit.setDiseases(diseases);
        visit.setMedicalServices(medicalServices);
        //fixme check if save is needed
    }

    public void changeVisitStatus(long visitId, VisitStatus status){
        Visit visit = visitRepository.findById(visitId).orElseThrow(RuntimeException::new);
        visit.setStatus(status);
    }
}
