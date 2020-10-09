/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-09 10:16:18
 * @modify date 2020-10-09 10:16:18
 * @desc status order
 */
package com.fpineda.samples.choreographypattern.core.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    
    PLACED(1), COMMITED(2);

    private final int value;

    private OrderStatus(int value) {
        this.value = value;
    }

}
