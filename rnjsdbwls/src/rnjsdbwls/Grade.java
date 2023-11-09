package rnjsdbwls;

import java.util.ArrayList;
import java.util.Scanner;

class Student{
	
	String name;
	int  grade;
	int score;
	
	Student(String name, int grade, int score){
		this.name = name;
		this.grade = grade;
		this.score = score;
		
	}
}

class GradeBook{
	String name;
	int grade;
	int score;
	
	GradeBook(String name, int grade, int score){
		this.name = name;
		this.grade = grade;
		this.score = score;
		
		
	}
	void summ() {
		double result = 0;
		
		
		
	}
	
	
}
public class Grade {

	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		ArrayList<Student> list = new ArrayList<>();
		System.out.println("학생 수를 입력하시오");
		int st = scan.nextInt();
		int sum = 0;
		for(int i=0;i<st;i++) {
		System.out.println("이름을 입력하시오");
		String name = scan.next();
		
		System.out.println("학번을 입력하시오");
		int grade = scan.nextInt();
		
		System.out.println("점수를 입력하시오");
		int score = scan.nextInt();
		
		list.add(new Student(name,grade,score));
		sum += score;
		
		
		
		}
		
		double result = sum / st;
		System.out.println(list);
		System.out.println("전체 학생은 총" + st +"명 이고, 학생들의 전체 평균 점수는 " + result + "입니다");
		
		
		
		// TODO Auto-generated method stub

	}

}
