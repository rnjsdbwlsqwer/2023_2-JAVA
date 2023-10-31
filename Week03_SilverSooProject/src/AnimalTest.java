interface Animal{
	void walk();
	void fly();
	void sing();
}

class Bird implements Animal{
	public void walk() {
		System.out.println("걸을 수 있음");
	}
	public void fly() {
		System.out.println("날을 수 있음");
	}
	public void sing() {
		System.out.println("노래 부를 수 있음");
	}
}

public class AnimalTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Bird b = new Bird();
		b.walk();
		b.fly();
		b.sing();

	}

}
