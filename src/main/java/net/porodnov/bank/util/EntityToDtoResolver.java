package net.porodnov.bank.util;

import liquibase.pro.packaged.T;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityToDtoResolver {
    private T entity;
    private T dto;

    public EntityToDtoResolver() {
    }
/*
    public T resolve(T entity) {
        Class<? extends T> classEntity = entity.getClass();
        Arrays.stream(classEntity.getConstructors()).map(constructor -> constructor.get);
    }*/
}
