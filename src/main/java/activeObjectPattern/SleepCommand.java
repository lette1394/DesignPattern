package activeObjectPattern;

public class SleepCommand implements Command {
    private Command wakeupCommand = null;
    private ActiveObjectEngine engine = null;
    private long sleepTimes = 0;
    private long startTimes = 0;
    private boolean started = false;

    public SleepCommand(
            long milliseconds,
            ActiveObjectEngine engine,
            Command wakeupCommand) {
        this.sleepTimes = milliseconds;
        this.engine = engine;
        this.wakeupCommand = wakeupCommand;
    }

    @Override
    public void execute() {
        long currentTime = System.currentTimeMillis();
        if (!started) {
            started = true;
            startTimes = currentTime;
            engine.addCommand(this);
        } else if ((currentTime - startTimes) < sleepTimes) {
            engine.addCommand(this);
        } else {
            engine.addCommand(wakeupCommand);
        }
    }
}
