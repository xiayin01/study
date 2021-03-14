package com.xy.user.web.jolokia;

import com.xy.user.web.domain.Color;
import sun.management.Util;

import javax.management.ObjectName;

public class ColorImpl implements ColorMXBean {

    private final Color color;

    public ColorImpl(Color color) {
        this.color = color;
    }

    @Override
    public String getColorName() {
        return this.color.getName();
    }

    @Override
    public void setColorName(String name) {
        System.out.println("---调用setColor()---");
        this.color.setName(name);
    }

    /**
     * Returns an {@link ObjectName ObjectName} instance representing
     * the object name of this platform managed object.
     *
     * @return an {@link ObjectName ObjectName} instance representing
     * the object name of this platform managed object.
     */
    @Override
    public ObjectName getObjectName() {
        return Util.newObjectName("com.xy.user.web.jolokia:type=Color");
    }
}
