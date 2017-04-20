package exam;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogProxy implements InvocationHandler {

	private Object target;
	private IObserver observer;

	public LogProxy(Object o) {
		target = o;
	}

	@Override
	public Object invoke(Object obj, Method m, Object[] args) throws Throwable {
		Object result = null;

		if (m.getName().equals("setObserver")) {
			observer = (IObserver) args[0];
		} else {
			if (observer != null) {
				if (m.getName().equals("setNom")) {
					observer.notify(target);
				}
			}

			result = m.invoke(target, args);
		}

		return result;
	}

}
