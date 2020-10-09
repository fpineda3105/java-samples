/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-08 23:32:48
 * @modify date 2020-10-08 23:32:48
 * @desc [description]
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
public class PlaceOrderEvent {

    private final long id;
    
}
