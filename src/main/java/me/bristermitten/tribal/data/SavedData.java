package me.bristermitten.tribal.data;

import lombok.extern.slf4j.Slf4j;
import me.bristermitten.tribal.io.StorageType;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Slf4j
public abstract class SavedData {
    private transient final StorageType type;
    private final ManagedFile file;

    protected SavedData(File folder, StorageType type, String fileName) {
        this.type = type;
        this.file = ManagedFile.ofFile(folder)
                .getSubFile(String.format("%s.%s", fileName, type.getFileExtension()));
    }

    public void save() {
        try {
            file.createFileIfNotExist();
            OutputStreamWriter writer = new OutputStreamWriter(file.outputStream());
            type.save(getDataToSave(), writer);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            log.error(getClass().getSimpleName() + " Failed to save to " + file.getFile().getPath(), e);
        }
    }

    protected abstract Object getDataToSave();
}
