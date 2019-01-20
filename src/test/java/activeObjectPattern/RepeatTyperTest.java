package activeObjectPattern;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class RepeatTyperTest {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    private void simulate(final String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(new PrintStream(out));
    }

    @RepeatedTest(10)
    void typed_every_1_second() throws InterruptedException {
        ActiveObjectEngine engine = new ActiveObjectEngine();
        simulate("");

        RepeatTyper typer1 = new RepeatTyper('a', 1000, engine);
        engine.addCommand(typer1);

        RepeatTyper typer2 = new RepeatTyper('b', 600, engine);
        engine.addCommand(typer2);

        RepeatTyper typer3 = new RepeatTyper('c', 200, engine);
        engine.addCommand(typer3);

        engine.addCommand(new StopCommand(typer1));
        engine.addCommand(new StopCommand(typer2));
        engine.addCommand(new StopCommand(typer3));

        assertTimeoutPreemptively(Duration.ofMillis(1000), engine::run);

        assertThat(out.toString()).isEqualTo("abccba");
    }
}
