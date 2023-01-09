package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class MyExample {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new DummyEnvironment();
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }
    @Test
    public void PayerInna_is_Girl() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        assertSame(container.getComponent(Girl.class), container.getComponent(PlayerInna.class));
    }
    @Test
    public void PayerArtem_is_Boy() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        assertSame(container.getComponent(Boy.class), container.getComponent(PlayerArtem.class));
    }

    @Test
    public void EachPlayerHasDifferentServeInVolleyball() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        People player1 = container.getComponent(People.class);
        People player2 = container.getComponent(People.class);
        assertNotSame(player1.getServingTheBall(), player2.getServingTheBall());
    }

    @Test
    public void EachPlayerHasDifferentReceptionInVolleyball() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        People player1 = container.getComponent(People.class);
        People player2 = container.getComponent(People.class);
        assertNotSame(player1.getReceptionOfBall(), player2.getReceptionOfBall());
    }

}
