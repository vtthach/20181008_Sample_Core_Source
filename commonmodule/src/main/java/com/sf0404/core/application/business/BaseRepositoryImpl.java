package com.sf0404.core.application.business;

public abstract class BaseRepositoryImpl<T> {

    protected T service;

    public BaseRepositoryImpl(T service) {
        this.service = service;
    }
}
