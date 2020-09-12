package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.actions.AddChatMessageAction;
import org.beuwi.msgbots.platform.gui.control.ListView;
import org.beuwi.msgbots.platform.gui.control.TabPane;

public class DebugAreaPart implements View
{
	private static final int MAX_WIDTH = 000;
	private static final int MIN_WIDTH = 350;

	private static ObservableMap<String, Object> nameSpace;

	private static FormLoader loader;

	private static AnchorPane root;

	private static TabPane component;

	private static Pane resize;

	@Override
	public void init() throws Exception
	{
		loader = new FormLoader("debug-area-part");
		nameSpace = loader.getNamespace();
		root = loader.getRoot();

		component = (TabPane) loader.getComponent();

		// Resize Bar
		resize = (Pane) nameSpace.get("resize");

		resize.setOnMouseDragged(event ->
		{
			double size = MainView.getStage().getWidth() - (event.getSceneX() + 16);

			if (MIN_WIDTH < size)
			{
				root.setPrefWidth(size);
			}
		});

		ListView listView = (ListView) nameSpace.get("lsv-chat-list");
		TextArea textArea = (TextArea) nameSpace.get("txa-chat-input");

		textArea.setOnKeyPressed(event ->
		{
			if (event.getCode().equals(KeyCode.ENTER))
			{
				if (event.isShiftDown())
				{
					textArea.appendText(System.lineSeparator());
					event.consume();
					return ;
				}

				String text = textArea.getText();

				if (text.trim().isEmpty())
				{
					return ;
				}

				// Action
				AddChatMessageAction.execute(text);

				textArea.clear();
				event.consume();
			}
		});

		textArea.requestFocus();
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static TabPane getComponent()
	{
		return component;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}
