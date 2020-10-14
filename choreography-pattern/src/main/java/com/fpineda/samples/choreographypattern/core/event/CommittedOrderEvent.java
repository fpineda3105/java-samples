/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-13 18:59:29
 * @modify date 2020-10-13 18:59:29
 * @desc Order committed event
 */
package com.fpineda.samples.choreographypattern.core.event;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class CommittedOrderEvent extends Event {
    
    private final long orderId;
}
