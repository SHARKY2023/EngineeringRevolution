package com.sharky2023.engineeringrevolution.api.MultiBlock.v2;

@FunctionalInterface
public interface TriPredicate<A, B, C> {
    boolean test(A a, B b, C c);
}