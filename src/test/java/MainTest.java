import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    @Timeout(value = 21, unit = TimeUnit.SECONDS)
    void main() throws Exception {
        String[] args = new String[]{};
        Main.main(args);
    }
}