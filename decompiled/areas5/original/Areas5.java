// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Areas5.java

import java.applet.Applet;
import java.awt.*;

public class Areas5 extends Applet
{

    public Areas5()
    {
    }

    public void init()
    {
        l1 = new Label("\u0412\u0430\u0440\u0438\u0430\u043D\u0442");
        t1 = new TextField(4);
        p1 = new Panel();
        p1.setLayout(new FlowLayout());
        p1.add(l1);
        p1.add(t1);
        setLayout(new BorderLayout());
        add(p1, "North");
        c1 = new AreaCanvas5();
        t1.addActionListener(c1);
        add(c1, "Center");
        validate();
    }

    Label l1;
    TextField t1;
    Panel p1;
    AreaCanvas5 c1;
}
