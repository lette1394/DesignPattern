package activeObjectPattern;

import java.util.LinkedList;
import java.util.List;

public class ActiveObjectEngine {
    private List<Command> commands = new LinkedList<>();

    public void addCommand(Command c) {
        commands.add(c);
    }

    public void run() {
        while (!commands.isEmpty()) {
            Command c = commands.get(0);
            commands.remove(0);
            c.execute();
        }
    }
}
