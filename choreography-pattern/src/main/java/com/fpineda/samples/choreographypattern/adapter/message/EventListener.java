/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:31:16
 * @modify date 2020-10-08 23:31:16
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.adapter.message;

public interface EventListener <T> {

    void handleEvent(T event);
    
}
