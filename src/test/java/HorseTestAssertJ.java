import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HorseTestAssertJ {
    Horse horseAllArgsConst;
    Horse horseWithoutInitDistance;

    @BeforeEach
    private void init() {
        horseAllArgsConst = new Horse("loshadka", 5, 3);
        horseWithoutInitDistance = new Horse("loshadka", 5);
    }

    @Test
    void getName() {
        assertThat(horseAllArgsConst.getName()).isEqualTo("loshadka");
    }

    @Test
    void getSpeed() {
        assertThat(horseAllArgsConst.getSpeed()).isEqualTo(5);
    }

    @Test
    void getDistance() {
        assertThat(horseAllArgsConst.getDistance()).isEqualTo(3);
    }

    @Test
    void getDistanceZero() {
        assertThat(horseWithoutInitDistance.getDistance()).isEqualTo(0);
    }

    @Test
    void initConstructorFirstParamNull(){
        assertThatThrownBy(() -> new Horse(null, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name cannot be null");
    }

    @Test
    void initConstructorSecondParamNegative(){
        assertThatThrownBy(() -> new Horse("loshadka", -1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Speed cannot be negative.");
    }

    @Test
    void initConstructorThirdParamNegative(){
        assertThatThrownBy(() -> new Horse("loshadka", 1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Distance cannot be negative.");
    }

    @Test
    void initConstructorFirstParamBlank(){
        assertThatThrownBy(()-> new Horse("", 1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name cannot be blank.");
    }

    //junit- need replace with assertj
    @ParameterizedTest
    @CsvSource({
       " , 1, 1",


    })
    void testMethod(String name, double speed, double distance) {
        assertThatThrownBy(()->new Horse(name, speed,distance)).isInstanceOf(IllegalArgumentException.class);
    }



    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
        try (MockedStatic<Horse> testHorse = Mockito.mockStatic(Horse.class)) {
            testHorse.when(this::getRandomDouble).thenReturn(horseAllArgsConst.getDistance() + 31);
        }
    }
}