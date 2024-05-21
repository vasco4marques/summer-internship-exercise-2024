package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.TeknonymyService;
import com.premiumminds.internship.teknonymy.Person;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TeknonymyServiceTest {

    /**
     * The corresponding implementations to test.
     *
     * If you want, you can make others :)
     *
     */
    public TeknonymyServiceTest() {
    };

    @Test
    public void PersonNoChildrenTest() {
        Person person = new Person("John", 'M', null, LocalDateTime.of(1046, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(person);
        String expected = "";
        assertEquals(expected, result);
        System.out.println(expected);
    }

    @Test
    public void PersonOneChildTest() {
        Person person = new Person(
                "John",
                'M',
                new Person[] { new Person("Holy", 'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
                LocalDateTime.of(1046, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(person);
        System.err.println("Result " + result);
        String expected = "father of Holy";
        assertEquals(expected, result);
    }

    @Test
    public void PersonMultipleChildrenTest() {
        Person personWithTwoChildren = new Person(
                "João",
                'M',
                new Person[] {
                        new Person("Vasco", 'M', null, LocalDateTime.of(2000, 1, 1, 0, 0)),
                        new Person("Carol", 'F', null, LocalDateTime.of(2002, 1, 1, 0, 0))
                },
                LocalDateTime.of(1975, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(personWithTwoChildren);
        String expected = "father of Vasco";
        assertEquals(expected, result);
    }

    @Test
    public void PersonWithMultipleChildrenAndGrandChildren() {
        Person personWithThreeGrandchildren = new Person(
                "David",
                'M',
                new Person[] {
                        new Person("Eva", 'F', new Person[] {
                                new Person("José", 'M', null, LocalDateTime.of(2020, 1, 1, 0, 0)),
                                new Person("Graça", 'F', null, LocalDateTime.of(2022, 1, 1, 0, 0)),
                                new Person("Maria", 'F', null, LocalDateTime.of(2024, 1, 1, 0, 0))
                        }, LocalDateTime.of(1990, 1, 1, 0, 0)),
                        new Person("Ricardo", 'M', null, LocalDateTime.of(1989, 1, 1, 0, 0)),
                },
                LocalDateTime.of(1965, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(personWithThreeGrandchildren);
        String expected = "grandfather of José";
        assertEquals(expected, result);
    }

    @Test
    public void PersonWithGreatGrandChild() {
        Person personWithGreatGrandchild = new Person(
                "Irene",
                'F',
                new Person[] {
                        new Person("Vasco", 'M', new Person[] {
                                new Person("Carla", 'F', new Person[] {
                                        new Person("Leandro", 'M', null, LocalDateTime.of(2050, 1, 1, 0, 0)),
                                        new Person("Marta", 'F', null, LocalDateTime.of(2052, 1, 1, 0, 0))
                                }, LocalDateTime.of(2025, 1, 1, 0, 0))
                        }, LocalDateTime.of(2000, 1, 1, 0, 0))
                },
                LocalDateTime.of(1970, 1, 1, 0, 0));
        String result = new TeknonymyService().getTeknonymy(personWithGreatGrandchild);
        String expected = "great-grandmother of Leandro";
        assertEquals(expected, result);
    }

    @Test
    public void PersonWithGreatGreatGrandchild() {
        Person personWithGreatGreatGrandchild = new Person(
                "Nancy",
                'F',
                new Person[] {
                        new Person("Oscar", 'M', new Person[] {
                                new Person("Paul", 'M', new Person[] {
                                        new Person("Quinn", 'F', new Person[] {
                                                new Person("Ruby", 'F', null, LocalDateTime.of(2075, 1, 1, 0, 0))
                                        }, LocalDateTime.of(2050, 1, 1, 0, 0))
                                }, LocalDateTime.of(2025, 1, 1, 0, 0))
                        }, LocalDateTime.of(2000, 1, 1, 0, 0))
                },
                LocalDateTime.of(1975, 1, 1, 0, 0));

        String result = new TeknonymyService().getTeknonymy(personWithGreatGreatGrandchild);
        String expected = "great-great-grandmother of Ruby";
        assertEquals(expected, result);
    }

    @Test
    public void PersonWithBigFamilyTree() {
        // Confusa esta árvore

        // Matilde tem 3 filhos: Lara, Mario e Nicole
        // Lara tem 2 filhos: Maria, Pedro
        // Pedro tem 0 fihos
        // Maria tem 1 filha: Patricia
        // Patricia tem 1 filha: Clara
        // Clara tem 1 filha: Iris
        // Iris tem 1 filha: Paula
        // Mario tem 1 filho: João
        // João tem 1 filho: Paula
        // Nicole tem 0 filhos

        Person personWithDeepFamilyTree = new Person(
                "Matilde",
                'F',
                new Person[] {
                        new Person("Lara", 'F', new Person[] {
                                new Person("Maria", 'F', new Person[] {

                                        new Person("Patricia", 'F', new Person[] {

                                                new Person("Clara", 'M', new Person[] {

                                                        new Person("Iris", 'F', new Person[] {
                                                                new Person("Paula", 'F', null,
                                                                        LocalDateTime.of(2125, 1, 1, 0, 0))
                                                        },
                                                                LocalDateTime.of(2100, 1, 1, 0, 0))
                                                }, LocalDateTime.of(2075, 1, 1, 0, 0))
                                        }, LocalDateTime.of(2050, 1, 1, 0, 0))
                                }, LocalDateTime.of(2025, 1, 1, 0, 0)),

                                new Person("Pedro", 'M', null, LocalDateTime.of(2030, 1, 1, 0, 0))
                        }, LocalDateTime.of(2000, 1, 1, 0, 0)),

                        new Person("Mario", 'M', new Person[] {
                                new Person("João", 'M', new Person[] {
                                        new Person("Paula", 'F', null, LocalDateTime.of(2045, 1, 1, 0, 0))
                                }, LocalDateTime.of(2020, 1, 1, 0, 0))
                        }, LocalDateTime.of(2002, 1, 1, 0, 0)),

                        new Person("Nicole", 'F', null, LocalDateTime.of(2005, 1, 1, 0, 0))
                },
                LocalDateTime.of(1970, 1, 1, 0, 0));

        String result = new TeknonymyService().getTeknonymy(personWithDeepFamilyTree);
        String expected = "great-great-great-great-grandmother of Paula";
        assertEquals(expected, result);
    }
}