/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-09 15:21:50
 * @modify date 2020-10-09 15:21:50
 * @desc [description]
 */
package com.fpineda.samples.choreographypattern.core.ports;

public interface CommitProductStockPort {

    boolean commit(long productId, int quantity);
}
