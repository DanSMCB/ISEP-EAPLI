package eapli.base.Classe.aplication;

import eapli.base.Classe.domain.*;
import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;


import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ClassController{

    private final ClassRepository repository;
    private TeacherRepository teacherRepository;
    public ClassController(ClassRepository repository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
    }


    public void changeClassTitle(Long id, eapli.base.Classe.domain.Classe_Title newTitle) throws IntegrityViolationException, ConcurrencyException {
        Optional<Classe> optionalClass = this.repository.ofIdentity(id);
        if (optionalClass.isPresent()) {
            Classe classeToChange = optionalClass.get();
            classeToChange.changeTitle(newTitle);
            this.repository.save(classeToChange);
        }
    }

    public void deleteClass(Long id) throws IntegrityViolationException, ConcurrencyException {
        Optional<Classe> optionalClass = this.repository.ofIdentity(id);
        if (optionalClass.isPresent()) {
            this.repository.delete(optionalClass.get());
        }
    }
    public Classe scheduleClass(Long id, eapli.base.Classe.domain.Classe_Title title, Classe_Start_Time startTime, Classe_Finish_Time finishTime, Classe_Start_Date start_date, Classe_Finish_Date finishDate, DayOfWeek day, Acronym teacherAcronym) throws IntegrityViolationException, ConcurrencyException {

        // Creating a new Teacher instance using the given Acronym
       // Teacher teacher = new Teacher(teacherAcronym);

       // Set<Teacher> teachers = new HashSet<>();
       // teachers.add(teacher);

        final Classe newClasse = new Classe(id, title, start_date, finishDate, startTime, finishTime, day, teacherAcronym);

        if (checkClassConflict(newClasse)) {
            throw new IllegalStateException("There is a scheduling conflict with this class.");
        } else {
            return this.repository.save(newClasse);
        }
    }


   /* public Classe scheduleClass(Long id, eapli.base.Classe.domain.Classe_Title title, Classe_Start_Time startTime, Classe_Finish_Time finishTime, Classe_Start_Date start_date, Classe_Finish_Date finishDate, DayOfWeek day, Acronym teacherAcronym) throws IntegrityViolationException, ConcurrencyException {
       Optional<Teacher> teacherOptional = TeacherRepository.findTeacherByAcronym(teacherAcronym);
        if (teacherOptional.isEmpty()) {
            throw new IllegalArgumentException("No teacher found with provided acronym");
        }
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(teacherOptional.get());
        final Classe newClasse = new Classe(id, title, start_date, finishDate, startTime, finishTime, day, teacherAcronym);

        if (checkClassConflict(newClasse)) {
            throw new IllegalStateException("There is a scheduling conflict with this class.");
        } else {
            return this.repository.save(newClasse);
        }
    }

    */



    public boolean checkClassConflict(Classe classeToCheck) {
        List<Classe> allClasses = (List<Classe>) repository.findAll();
        for (Classe existingClass : allClasses) {
            if (existingClass.getId().equals(classeToCheck.getId())) {
                // Skip checking against the same class
                continue;
            }

            // Only compare classes on the same date
            if (existingClass.getStart_date().equals(classeToCheck.getStart_date())) {
                // Check for time overlap
                if (isTimeOverlapUserInput(classeToCheck.getStart_time(), classeToCheck.getFinish_time(), existingClass)) {
                    return true; // Conflict found
                }
            }
        }

        return false; // No conflict found
    }

    /**
     * Checks if the class specified by userInputStartTime and userInputEndTime overlaps
     * with the existing class's start and end times.
     *
     * @param userInputStartTime The start time of the class to check.
     * @param userInputEndTime The end time of the class to check.
     * @param existingClass The class with which to compare the given class.
     *
     * @return True if the given class overlaps with the existing class, false otherwise.
     */
    public boolean isTimeOverlapUserInput(Classe_Start_Time userInputStartTime, Classe_Finish_Time userInputEndTime, Classe existingClass) {
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




    public Classe updateClassSchedule(Long id, Classe_Start_Time newStartTime, Classe_Finish_Time newFinishTime) throws IntegrityViolationException, ConcurrencyException {

        Optional<Classe> optionalClass = this.repository.ofIdentity(id);
        if (optionalClass.isPresent()) {
            Classe classToUpdate = optionalClass.get();
            classToUpdate.change_startTime(newStartTime);
            classToUpdate.change_finishTime(newFinishTime);
            return this.repository.save(classToUpdate);
        } else {
            throw new IllegalArgumentException("No class found with ID: " + id);
        }
    }
    public List<Classe> getAllClasses() {
        return repository.findAll();
    }



}



