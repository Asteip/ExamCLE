package exam;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		IPersonne pers = (IPersonne) getProxyFor(new Personne());
		pers.setNom("toto");
		IObserver observer = new Observer();
		((IObservable) pers).setObserver(observer);
		pers.setNom("toto");
	}

	public static Object getProxyFor(Object o) {
		Object result = null;
		
		Class<?>[] oInterfaces = o.getClass().getInterfaces();
		Class<?>[] oCpyInterfaces = Arrays.copyOf(oInterfaces, oInterfaces.length + 1);
		oCpyInterfaces[oCpyInterfaces.length - 1] = IObservable.class;
		
		result = Proxy.newProxyInstance(o.getClass().getClassLoader(), oCpyInterfaces, new LogProxy(o));
		
		return result;
	}

}
