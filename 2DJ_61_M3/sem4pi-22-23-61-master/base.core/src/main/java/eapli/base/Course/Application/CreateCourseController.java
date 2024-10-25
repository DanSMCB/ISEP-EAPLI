package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;

public class CreateCourseController {

    private final CourseService cs;

    public CreateCourseController(CourseService cs){
        this.cs = cs;
    }

    public Course createCourse(Course course){
        return cs.createCourse(course);
    }
}
