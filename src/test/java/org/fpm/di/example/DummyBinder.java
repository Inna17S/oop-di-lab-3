package org.fpm.di.example;

import org.fpm.di.Binder;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class DummyBinder implements Binder {
    HashMap<Class<?>, Class<?>> implementationDependenciesMap = new HashMap<>();
    HashMap<Class<?>, Object> instanceMap = new HashMap<>();
    HashMap<Class<?>, Constructor<?>> constructors = new HashMap<>();
    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {implementationDependenciesMap.put(clazz, implementation);
    }
    @Override
    public <T> void bind(Class<T> clazz) {
        for(Constructor<?> constructor : clazz.getConstructors()) {
            if(constructor.isAnnotationPresent(Inject.class)) {
                constructors.put(clazz, constructor);
            }
        }
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {instanceMap.put(clazz, instance);
    }
    @Override
    public void bind(Object clazz) {
    }

    @Override
    public void bind(Object clazz, Object instance) {
    }
}