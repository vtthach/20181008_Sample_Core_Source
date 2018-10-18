package com.sf0404.core.application.business.mapper;

public interface BaseResponseMapper<T, V> {
    V transformFromResponse(T from);
}
