package com.logilite.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Example {
	public static void main(String[] args) {

		Instant t1 = Instant.now();
		String csvFile = "/home/bhautik/Downloads/students.csv";
		String line;
		String csvDelimiter = ","; // Specify the delimiter used in your CSV file

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(csvDelimiter);

				// Process the data in each row
				for (String value : data) {
					System.out.print(value + ", ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Instant t2 = Instant.now();
		Duration to = Duration.between(t1, t2);
		System.out.println(to);
	}
}
