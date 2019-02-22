package me.bristermitten.tribal.io;


import com.google.inject.Singleton;
import lombok.Getter;

@Singleton
public class Config {
    @Getter
    private StorageType storageType = StorageType.JSON;
}
