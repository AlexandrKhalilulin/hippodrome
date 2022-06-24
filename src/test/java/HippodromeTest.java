import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    @DisplayName("When passed to a null constructor, an IllegalArgumentException will be thrown")
    void initConstructorNull(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("When passing an empty list to the constructor, an IllegalArgumentException will be thrown")
    void initConstructorEmptyList(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Hippodrome.getHorses returns a list that contains the same objects and in the same order as the list" +
            " that was passed to the constructor")
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(""+ i, i+1));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 30; i++) {
            assertEquals(horses.get(i), hippodrome.getHorses().get(i));
        }
    }

    @Test
    @DisplayName("Hippodrome.move calls the move method on all horses")
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
    @DisplayName("Hippodrome.getWinner returns the horse with the largest distance value")
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            horses.add(new Horse("" + i, i + 1, i + 1 / 2));
        }
        Horse expected = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(expected, hippodrome.getWinner());
    }
}