package com.study.design.mode.annotation;

/**
 * 功能描述: <br>
 *
 * @author zjx
 * @version 1.0.0
 * @date 2021/4/25 9:51
 */
@FunctionalInterface
public interface IConvert<F,T> {

    T convert(F form);
}
