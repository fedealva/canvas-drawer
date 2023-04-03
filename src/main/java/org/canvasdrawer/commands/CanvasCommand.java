package org.canvasdrawer.commands;

import org.canvasdrawer.entities.Canvas;

public interface CanvasCommand extends Command {
    void execute(final Canvas canvas, final String[] params);

}
