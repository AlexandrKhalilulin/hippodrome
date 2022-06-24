import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseJUnitTest {
    private final String NAME_CANNOT_BLANK = "Name cannot be blank.";
    private final String NAME_CANNOT_NULL = "Name cannot be null.";
    private final String SPEED_CANNOT_NEGATIVE = "Speed cannot be negative.";
    private final String DISTANCE_CANNOT_NEGATIVE = "Distance cannot be negative.";
    Horse allArgsHorse = new Horse("loshadka", 1, 3);
    Horse twoArgsHorse = new Horse("loshadka", 1);

    @Test
    @DisplayName("Horse.getName returns the string that was passed as the first parameter to the constructor")
    void getName() {
        assertEquals("loshadka", allArgsHorse.getName());
    }

    @Test
    @DisplayName("Horse.getSpeed returns the number that was passed as the second parameter to the constructor")
    void getSpeed() {
        assertEquals(1, allArgsHorse.getSpeed());
    }

    @Test
    @DisplayName("Horse.getDistance returns the number that was passed as the third parameter to the constructor")
    void getDistance() {
        assertEquals(3, allArgsHorse.getDistance());
        }

    @Test
    @DisplayName("Horse.getDistance returns null if the object was created using a constructor with two parameters")
    void getDistanceZero(){
        assertEquals(0, twoArgsHorse.getDistance());
    }

    @ParameterizedTest
    @DisplayName("When passing an empty string or a string containing only whitespace characters (space, tab, etc.)" +
            " as the first parameter to the constructor, an IllegalArgumentException will be thrown")
    @ValueSource(strings = {" ","", "    ", "\t", "\n\n\n"})
    void initConstructorNameParamBlankOrSpace(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals(NAME_CANNOT_BLANK, exception.getMessage());
    }

    @Test
    @DisplayName("When passed to the constructor as the name parameter null, the thrown IllegalClassException")
    void initConstructorNameParamNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals(NAME_CANNOT_NULL, exception.getMessage());
    }

    @Test
    @DisplayName("When passing a negative number as the speed parameter to the constructor, an IllegalArgumentException" +
            " will be thrown")
    void initConstructorSpeedParamNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("loshadka", -2, 1));
        assertEquals(SPEED_CANNOT_NEGATIVE, exception.getMessage());
    }

    @Test
    @DisplayName("when passing a negative number as the distance parameter to the constructor, an IllegalArgumentException will be thrown")
    void initConstructorDistanceParamNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("loshadka", 1, -2));
        assertEquals(DISTANCE_CANNOT_NEGATIVE, exception.getMessage());
    }

    @Test
    @DisplayName("Horse.move calls inside the getRandomDouble method with parameters 0.2 and 0.9")
    void moveUseGetRandomDoubleWithParams() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            allArgsHorse.move();
            horseMockedStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @DisplayName("Horse.move assigns the distance value calculated by the formula")
    @ValueSource(doubles= {2, 4, 5})
    void moveSetRightDistance(double valueFromGetRandomDouble){
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(valueFromGetRandomDouble);
            double expected = allArgsHorse.getDistance() + allArgsHorse.getSpeed() * valueFromGetRandomDouble;
            allArgsHorse.move();
            assertEquals(expected, allArgsHorse.getDistance());
        }
    }
}