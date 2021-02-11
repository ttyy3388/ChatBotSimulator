package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.layout.AnchorPane;
import org.beuwi.msgbots.platform.gui.layout.HBox;

public class UpdateStatusBarAction implements Action {
    private static Label lblCurrentPath;

    @Override
    public void init() {
        lblCurrentPath = (Label) StatusBarPart.getComponent("lblCurrentPath");
    }

    public static void execute(String[] data) {
        lblCurrentPath.setText(data[0]);
    }

    @Override
    public String getName() {
        return "open.document.tab.action";
    }
}
