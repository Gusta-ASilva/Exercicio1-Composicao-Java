package Application;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entitites.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		
		System.out.println("Enter worker data:");
		
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		String level = sc.nextLine();
		System.out.print("Base salary: ");
		double baseSalary = sc.nextDouble();
		
		Worker worker = new Worker(name, WorkerLevel.valueOf(level), baseSalary, new Department(departmentName));
		
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			System.out.print("Enter contract #" + (i+1) + " data: ");
			System.out.print("Date ()DD/MM/YYYY): ");
			LocalDate contractDate = LocalDate.parse(sc.next(),fmt1);
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract);
		}

		System.out.print("\nEnter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));
	
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
	}

}
