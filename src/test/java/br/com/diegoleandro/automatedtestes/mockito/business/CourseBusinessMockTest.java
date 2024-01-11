package br.com.diegoleandro.automatedtestes.mockito.business;

import br.com.diegoleandro.automatedtestes.mockito.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;

public class CourseBusinessMockTest {

    CourseService mockService;
    CourseBusiness business;

    @BeforeEach
    void setup() {
        //Given
        mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);
    }

    @Test
   void testCoursesRelatedToSpring_When_UsingASMock() {

        // when
        var filteredCourses = business.retrieveCoursesRelatedToString("Diego");

        // then
        Assertions.assertEquals(4, filteredCourses.size());
   }
}
