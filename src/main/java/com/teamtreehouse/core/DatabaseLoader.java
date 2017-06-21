package com.teamtreehouse.core;

import com.teamtreehouse.course.Course;
import com.teamtreehouse.course.CourseRepository;
import com.teamtreehouse.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courses;

    @Autowired
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Basics", "http://teamtreehouse.com/java-basics");
        course.addReview(new Review(4, "Amazing course!!!"));
        course.addReview(new Review(5, "Changing life course!!!"));
        courses.save(course);

        String[] templates = {
                "Up and Running with %s",
                "%s for beginners",
                "%s Basics",
                "Under the hood: %s",
                "On the road with %s"
        };

        String[] buzzWords = {
                "Java",
                "Angular",
                "JavaScript",
                "Spring"
        };

        List<Course> bunchOfCourses = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> {
            String template = templates[i % templates.length];
            String buzzWord = buzzWords[i % buzzWords.length];

            String title = String.format(template, buzzWord);
            Course c = new Course(title, "http://teamtreehouse.com/");
            c.addReview(new Review(i % 5, String.format("More %s please", buzzWord)));

            bunchOfCourses.add(c);
        });

        courses.save(bunchOfCourses);
    }
}
