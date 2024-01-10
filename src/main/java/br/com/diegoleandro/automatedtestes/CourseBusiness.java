package br.com.diegoleandro.automatedtestes;

import br.com.diegoleandro.automatedtestes.mockito.CourseService;

import java.util.ArrayList;
import java.util.List;

// SUT - Systtem Under Test
public class CourseBusiness {

    private CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }


    public List<String> retrieveCoursesRelatedToString(String student) {

        var filteredCourses = new ArrayList<String>();
        var allCourses = service.retrieveCourses(student);

        for (String course: allCourses) {
            if (course.contains("Spring")) {
                filteredCourses.add(course);
            }
        }

        return filteredCourses;


    }
}
