/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-15 08:07:35
 * @modify date 2020-10-15 08:07:35
 * @desc Event source contract
 */
package com.fpineda.samples.choreographypattern.core.event;

import java.util.concurrent.CompletableFuture;

public interface EventSource <T> {
    
    CompletableFuture<Void> emit(T event);
}
