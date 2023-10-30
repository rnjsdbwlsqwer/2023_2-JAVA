package ch06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MethodReferenceLowerCase {

	public static void main(String[] args) {
		
		List<String> listOfNames = Arrays.asList(new String[]
				{"Apple", "Banana", "Cherry"});
		
		Stream<String> s1 = listOfNames.stream()
				.map(String::toLowerCase);
		
		s1.forEach(System.out::println);
		
		
				}
		// TODO Auto-generated method stub

	}
