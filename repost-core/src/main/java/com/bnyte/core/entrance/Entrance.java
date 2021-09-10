package com.bnyte.core.entrance;

/**
 * @auther bnyte
 * @date 2021-09-06 15:55
 * @email bnytezz@163.com
 */
public interface Entrance {

    Entrance initialize ();

    Entrance interceptor ();

    Entrance execute ();

    byte[] originData ();


}
