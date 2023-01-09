package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class DummyContainer implements Container {
    DummyBinder dummyBinder;
    public DummyContainer(DummyBinder dummyBinder) {
        this.dummyBinder = dummyBinder;
    }
    @Override
    public <T> T getComponent(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        if(dummyBinder.instanceMap.containsKey(clazz)){
            return (T) dummyBinder.instanceMap.get(clazz);
        }
        if(dummyBinder.implementationDependenciesMap.containsKey(clazz)){
            return getComponent((Class<T>)dummyBinder.implementationDependenciesMap.get(clazz));
        }
        T type;
        if(dummyBinder.constructors.containsKey(clazz)){
            Constructor<?> cons = dummyBinder.constructors.get(clazz);
            Parameter[] params = cons.getParameters();
            Object[] args = new Object[params.length];
            int i;
            for(i = 0; i < params.length; i++){
                args[i] = getComponent(params[i].getType());
            }
            type = (T) cons.newInstance(args);
        }
        else{
            type= clazz.getDeclaredConstructor().newInstance();
        }
        if(clazz.isAnnotationPresent(Singleton.class)){
            dummyBinder.bind(clazz, type);
        }
        return type;

    }
}
