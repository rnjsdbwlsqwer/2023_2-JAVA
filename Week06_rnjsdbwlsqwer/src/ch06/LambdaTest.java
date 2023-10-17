package ch06;
interface ArrayProcessing{
	double apply(double[] array);
}
public class LambdaTest {
	
	public static final ArrayProcessing maxer = array->{
		double max = array[0];
		
		for(double num:array) {
			if(num>max) {
				max = num;
			}
		}
		return max;
		
		
	};
	public static final ArrayProcessing miner = array->{
		double min = array[0];
		
		for(double num:array) {
			if(num<min) {
				min = num;
			}
		}
		return min;
		
		
		
	};
	
	public static final ArrayProcessing sumer = array->{
		double sum = 0;
		for(double num:array) {
			sum += num;
		}
		
		return sum;
	};

	
	public static void main(String[] args) {
		
		
	double [] array = {10, 20, 30, 40, 50};
	double maxresult = maxer.apply(array);
	double minresult = miner.apply(array);
	double sumresult = sumer.apply(array);
	
	System.out.println(maxresult);
	System.out.println(minresult);
	System.out.println(sumresult);
	
	}
}
