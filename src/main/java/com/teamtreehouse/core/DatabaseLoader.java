package com.teamtreehouse.core;

import com.teamtreehouse.course.Course;
import com.teamtreehouse.course.CourseRepository;
import com.teamtreehouse.review.Review;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courses;
    private final UserRepository users;

    @Autowired
    public DatabaseLoader(CourseRepository courses, UserRepository users) {
        this.courses = courses;
        this.users = users;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> students = Arrays.asList(
                new User("alex.andrade", "Alex","Andrade", "123", new String[]{"ROLE_USER"}),
                new User("user2", "User2","", "1234", new String[]{"ROLE_USER", "ROLE_ADM"})
        );
        users.save(students);

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
            Review review = new Review((i % 5) + 1, String.format("More %s please", buzzWord));
            review.setReviewer(students.get(i % students.size()));
            c.addReview(review);

            bunchOfCourses.add(c);
        });

        courses.save(bunchOfCourses);
    }
}
