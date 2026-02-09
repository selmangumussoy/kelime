package com.software.mywordbox.library.utils;

@FunctionalInterface
public interface Callable<T, R> {
    T call(R r);
}