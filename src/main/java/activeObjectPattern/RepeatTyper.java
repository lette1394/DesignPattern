package activeObjectPattern;

public class RepeatTyper implements Command, Switch {
    private char toTypedChar;
    private int delayedTime;
    private boolean shouldContinue = true;
    private ActiveObjectEngine engine;

    public RepeatTyper(
            final char toTypedChar,
            final int repeatTime,
            final ActiveObjectEngine engine) {
        this.toTypedChar = toTypedChar;
        this.delayedTime = repeatTime;
        this.engine = engine;
    }

    @Override
    public void execute() {
        System.out.print(toTypedChar);
        if (shouldContinue) {
            repeat();
        }
    }

    @Override
    public void turnOff() {
        this.shouldContinue = false;
    }

    private void repeat() {
        engine.addCommand(new SleepCommand(delayedTime, engine, this));
    }
}
