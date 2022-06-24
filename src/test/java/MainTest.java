import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Main method is executed no longer than 21 seconds")
    @Disabled
    @Timeout(value = 21, unit = TimeUnit.SECONDS)
    void main() throws Exception {
        Main.main(null);
    }
}