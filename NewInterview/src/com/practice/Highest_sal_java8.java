package com.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Highest_sal_java8 {

	public static void main(String[] args) {
		List<Emp>list=Arrays.asList(new Emp(1, "Dhiraj", 50000),
				                    new Emp(2, "Kunal", 40000),
				                     new Emp(3, "Om", 25000),
				                      new Emp(4, "Pravin", 30000));
		
				 System.out.println("Highest Salary is...");
				 Optional<Integer>sal= list.stream().map(x->x.getSalary())
				 .sorted(Comparator.reverseOrder()).findFirst();
				System.out.println(sal.get());
				
				
				
				
				
				
				
				
				

	}

}
