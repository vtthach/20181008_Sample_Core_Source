package com.sf0404.core.application.business.mapper;

public interface BaseRequestMapper<T, V> {
    V transformToRequest(T from);
}
