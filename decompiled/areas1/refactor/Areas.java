// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Areas.java

// Refactor by C.c (c) 2012

import java.applet.Applet;
import java.awt.*;

public class Areas extends Applet
{
    public Areas()
    {
    }

    public void init()
    {
        // Label & text field on a panel
		variantLabel = new Label("Variant");
        variantTextField = new TextField(4);
		
        mainPanel = new Panel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(variantLabel);
        mainPanel.add(variantTextField);
	
		// Adding a panel to the up of applet window
        setLayout(new BorderLayout());
		add(mainPanel, "North");
		
		// Adding an area canvas to center
        areaCanvas = new AreaCanvas();
		add(areaCanvas, "Center");
		
		// On text input event
        variantTextField.addActionListener(areaCanvas);
    
        validate();
    }

    Label variantLabel;
    TextField variantTextField;
    Panel mainPanel;
    AreaCanvas areaCanvas;
}
