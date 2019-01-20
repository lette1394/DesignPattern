package activeObjectPattern;

public class StopCommand implements Command {
    private final Switch _switch;

    public StopCommand(final Switch _switch) {
        this._switch = _switch;
    }

    @Override
    public void execute() {
        _switch.turnOff();
    }
}
