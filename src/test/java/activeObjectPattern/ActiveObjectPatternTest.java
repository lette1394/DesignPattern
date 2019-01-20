package activeObjectPattern;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ActiveObjectPatternTest {
    private boolean commandExecuted = false;

    @Test
    void test_for_sleep() {
        Command wakeup = () -> commandExecuted = true;

        ActiveObjectEngine engine = new ActiveObjectEngine();
        SleepCommand sleepCommand = new SleepCommand(1000, engine, wakeup);
        engine.addCommand(sleepCommand);

        long start = System.currentTimeMillis();
        engine.run();
        long stop = System.currentTimeMillis();
        long sleepTime = stop - start;

        System.out.println(sleepTime);
        assertThat(sleepTime >= 1000).isTrue();
        assertThat(sleepTime < 1100).isTrue();
        assertThat(commandExecuted).isTrue();
    }
}
