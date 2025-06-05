package moreoptions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Username: ");
//        String userName = scanner.nextLine();
//        System.out.println("Username is: " + userName);
//
//        System.out.println("Enter age and salary: ");
//        int age = scanner.nextInt();
//        double salary = scanner.nextDouble();
//
//        System.out.println("Age: " + age);
//        System.out.println("Salary: " + salary);

        // buffered reader is a better choice
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    }
}
