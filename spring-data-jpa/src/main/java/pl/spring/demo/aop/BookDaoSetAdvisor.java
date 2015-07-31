package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;

public class BookDaoSetAdvisor implements MethodBeforeAdvice {


	private void setId(Object arg, Object o) {
		if(arg instanceof BookEntity && ((BookEntity) arg).getId() == null){
			((BookEntity) arg).setId(((BookDaoImpl) o).getNextId());
		}
	}

	@Override
	public void before(Method method, Object[] objects, Object o) throws Throwable {
		if (hasAnnotation(method, o, NullableId.class)) {
			setId(objects[0], o);
		}
	}
	
	private boolean hasAnnotation(Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
		boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

		if (!hasAnnotation && o != null) {
			hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClazz) != null;
		}
		return hasAnnotation;
	}
}
