package br.com.diegoleandro.automatedtestes.mockito.business;


import br.com.diegoleandro.automatedtestes.mockito.service.CourseService;
import br.com.diegoleandro.automatedtestes.mockito.service.stubs.CourseServiceStubs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CourseBusinessStubTest {

    @Test
   void testCoursesRelatedToSpring_When_UsingAStub() {

        // Given
        CourseService stubService = new CourseServiceStubs();
        CourseBusiness business = new CourseBusiness(stubService);

        // when
        var filteredCourses = business.retrieveCoursesRelatedToString("Diego");

        // then
        Assertions.assertEquals(4, filteredCourses.size());
   }

   @Test
   void testCoursesRelatedToSpring_when_UsingAFooBarStudent() {

   }

}
