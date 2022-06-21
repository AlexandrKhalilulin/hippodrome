import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void initConstructorNull(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void initConstructorEmptyList(){
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            horses.add(new Horse(""+ i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for (int i = 0; i < 30; i++) {
            assertEquals(horses.get(i), hippodrome.getHorses().get(i));
        }
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse current : hippodrome.getHorses()
        ) {
            Mockito.verify(current).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            horses.add(new Horse("" + i, i+1));
        }
        Horse expected = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(expected, hippodrome.getWinner());
    }
}