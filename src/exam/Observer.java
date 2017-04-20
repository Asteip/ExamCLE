package exam;

public class Observer implements IObserver {
	public void notify(Object source){
		System.out.println(source + "is modified");
	}
}
