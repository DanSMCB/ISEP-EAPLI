package eapli.base.ExtraClasse.aplication;

import eapli.base.Classe.domain.*;
import eapli.base.ExtraClasse.domain.ExtraClasse_Day;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.ExtraClasse.domain.ExtraClasse;
import eapli.base.ExtraClasse.domain.ExtraClasse_Finish_Time;
import eapli.base.ExtraClasse.domain.ExtraClasse_Start_Time;
import eapli.base.ExtraClasse.domain.ExtraClasse_Title;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ExtraClassController {

    private final ClassRepository repository;
    private TeacherRepository teacherRepository;

    public ExtraClassController(ClassRepository repository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
    }

    public void deleteClass(Long id) throws IntegrityViolationException, ConcurrencyException {
        Optional<Classe> optionalClass = this.repository.ofIdentity(id);
        if (optionalClass.isPresent()) {
            this.repository.delete(optionalClass.get());
        }
    }

    public Classe scheduleExtraClass(Long id, ExtraClasse_Title title, ExtraClasse_Start_Time startTime, ExtraClasse_Finish_Time finishTime, ExtraClasse_Day day, Acronym teacherAcronym) throws IntegrityViolationException, ConcurrencyException {

        final ExtraClasse newExtraClasse = new ExtraClasse(id, title, startTime, finishTime, day, teacherAcronym);

        if (checkClassConflict(newExtraClasse)) {
            throw new IllegalStateException("There is a scheduling conflict with this class.");
        } else {
            return this.repository.save(newExtraClasse);
        }
    }

    public boolean checkClassConflict(ExtraClasse classeToCheck) {
        List<Classe> allClasses = (List<Classe>) repository.findAll();
        for (Classe existingClass : allClasses) {
            if (existingClass.getId().equals(classeToCheck.getId())) {
                // Skip checking against the same class
                continue;
            }

            // Only compare classes on the same day
            if (existingClass.getDay().equals(classeToCheck.getDay())) {
                // Check for time overlap
                if (isTimeOverlapUserInput(classeToCheck.getStart_time(), classeToCheck.getFinish_time(), (ExtraClasse) existingClass)) {
                    return true; // Conflict found
                }
            }
        }

        return false; // No conflict found
    }

    public boolean isTimeOverlapUserInput(ExtraClasse_Start_Time userInputStartTime, ExtraClasse_Finish_Time userInputEndTime, ExtraClasse existingClass) {
        // Extract the LocalTime from userInputStartTime and userInputEndTime
        LocalTime userInputStart = userInputStartTime.getStart_time();
        LocalTime userInputEnd = userInputEndTime.getFinish_time();

        // Convert start and end times of user input to minutes since midnight
        int userInputStartMinutes = userInputStart.getHour() * 60 + userInputStart.getMinute();
        int userInputEndMinutes = userInputEnd.getHour() * 60 + userInputEnd.getMinute();

        // Extract the LocalTime from existingClass's start and end time
        LocalTime existingStartTime = existingClass.getStart_time().getStart_time();
        LocalTime existingFinishTime = existingClass.getFinish_time().getFinish_time();

        // Convert start and end times of existing class to minutes since midnight
        int existingStartMinutes = existingStartTime.getHour() * 60 + existingStartTime.getMinute();
        int existingEndMinutes = existingFinishTime.getHour() * 60 + existingFinishTime.getMinute();

        // Check for overlap: does user input class start during existing class?
        if (userInputStartMinutes >= existingStartMinutes && userInputStartMinutes < existingEndMinutes) {
            return true; // Overlap found
        }

        // Check for overlap: does existing class start during user input class?
        if (existingStartMinutes >= userInputStartMinutes && existingStartMinutes < userInputEndMinutes) {
            return true; // Overlap found
        }

        // If neither condition above is met, there is no overlap
        return false; // No overlap found
    }
}
