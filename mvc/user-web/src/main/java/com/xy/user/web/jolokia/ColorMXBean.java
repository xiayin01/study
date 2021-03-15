package com.xy.user.web.jolokia;

import java.lang.management.PlatformManagedObject;

public interface ColorMXBean extends PlatformManagedObject {

    String getColorName();

    void setColorName(String name);
}
