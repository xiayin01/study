package com.xy.test.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author xy
 */
@Service
public class MyApplicationEvent {


    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        System.out.println("MyApplicationEvent...监听到的事件:" + event);
    }
}
