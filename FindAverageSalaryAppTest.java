package employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FindAverageSalaryAppTest {

    private FindAverageSalaryApp app;
    private List<Employee> employees;

    @BeforeEach
    public void setUp() {
        app = new FindAverageSalaryApp();
        employees = new ArrayList<>();
        employees.add(new Employee("Ashish", "A", "IT", "Pune", "Software Engineer", 10000.0));
        employees.add(new Employee("Amit", "R", "HR", "Pune", "Recruiter", 12000.0));
        employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Senior Recruiter", 14000.0));
        employees.add(new Employee("Jaya", "S", "IT", "Pune", "Tech Lead", 15000.0));
        employees.add(new Employee("Smita", "M", "IT", "Bangalore", "Recruiter", 16000.0));
        employees.add(new Employee("Umesh", "A", "IT", "Bangalore", "Software Engineer", 12000.0));
        employees.add(new Employee("Pooja", "R", "HR", "Bangalore", "Software Engineer", 12000.0));
        employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Recruiter", 16000.0));
        employees.add(new Employee("Bobby", "S", "IT", "Bangalore", "Tech Lead", 20000.0));
        employees.add(new Employee("Vipul", "M", "IT", "Bangalore", "Software Engineer", 14000.0));
    }

    @Test
    public void testFindAverageSalaryConcurrentAccess() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Runnable task = () -> {
            try {
                app.findAverageSalary(employees);
            } finally {
                latch.countDown();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        latch.await(); 
    }
}
