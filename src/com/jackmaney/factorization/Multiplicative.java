package com.jackmaney.factorization;

public interface Multiplicative<T> {

	public T multiply(T t);
	public T pow(int n);
	public T getIdentity();

}
