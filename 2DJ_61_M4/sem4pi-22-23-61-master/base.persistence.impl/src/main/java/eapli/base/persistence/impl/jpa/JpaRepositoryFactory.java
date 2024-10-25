/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.Enrollment.Repository.EnrollmentRepository;
import eapli.base.ExtraClasse.aplication.ExtraClassController;
import eapli.base.ExtraClasse.repository.ExtraClasseRepository;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.base.Student_Teacher.Student.Repository.StudentRepository;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.base.meetingmanagement.repository.MeetingRepository;
import eapli.base.systemUserManagement.SystemUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {


    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ExamRepository examRepository() {
        return new JpaExamRepository();
    }

    @Override
    public CourseRepository courseRepository() {
        return new JpaCourseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MeetingRepository meetingRepository() {
        return new JpaMeetingRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public InviteRepository inviteRepository() {
        return new JpaInviteRepository(PersistenceContext.repositories()
                .newTransactionalContext());
    }

    @Override
    public ClassRepository classRepository() {
        return new JpaClassRepository(PersistenceContext.repositories()
                .newTransactionalContext());
    }

    @Override
    public ExtraClasseRepository extraClassRepostory() {
        return new JpaExtraClassRepository(PersistenceContext.repositories()
                .newTransactionalContext());
    }

    @Override
    public TeacherRepository teacherRepository() {
        return new JpaTeacherRepository(PersistenceContext.repositories().newTransactionalContext());
    }

    @Override
    public StudentRepository studentRepository() {
        return new JpaStudentRepository(PersistenceContext.repositories().newTransactionalContext());
    }

    @Override
    public ClientUserRepository clientUserRepository() {
        return null;
    }

    @Override
    public SharedBoardRepository sharedBoardRepository() {
        return new JpaSharedBoardRepository(PersistenceContext.repositories().newTransactionalContext());
    }

    @Override
    public EnrollmentRepository enrollmentRepository() {
        return null;
    }

    @Override
    public SystemUserRepository systemUserRepository() {
        return new JpaSystemUserRepository(PersistenceContext.repositories().newTransactionalContext());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }


}
