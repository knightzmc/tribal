/*
 * MIT License
 *
 * Copyright (c) 2019 Alexander Wood
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.bristermitten.tribal.data;

import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import me.bristermitten.tribal.Tribal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

@EqualsAndHashCode
public class ManagedFile {
    private static Path tempDir;

    static {
        try {
            tempDir = Files.createTempDirectory("tribal-temp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final String path;
    private File file;

    public ManagedFile(String path) {
        this.path = path;
    }

    public ManagedFile(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public static ManagedFile ofFile(File file) {
        return new ManagedFile(file.getPath());
    }

    @SneakyThrows
    public static ManagedFile ofClasspathFile(String path) {
        InputStream is = Tribal.class.getResourceAsStream(path);
        Path target = newTempFile(path);
        Files.copy(is, target);
        return new ManagedFile(target.toFile());
    }

    @SneakyThrows
    private static Path newTempFile(String path) {
        return Files.createTempFile(tempDir, null, null);
    }

    @SneakyThrows
    public InputStream inputStream() {
        return new FileInputStream(getFile());
    }

    @SneakyThrows
    public OutputStream outputStream() {
        return new FileOutputStream(getFile());
    }

    public ManagedFile getSubFile(String name) {
        return new ManagedFile(path + File.separatorChar + name);
    }

    public ManagedFile getParent() {
        String parent = getFile().getParent();
        if (parent == null) return this;
        return new ManagedFile(parent);
    }

    public File getFile() {
        if (file == null)
            file = new File(path);
        return file;
    }


    public void createFileIfNotExist() {
        if (doesntExist()) {
            createFile();
        }
    }

    public void ifDoesntExist(Consumer<File> run) {
        if (doesntExist()) {
            run.accept(file);
        }
    }

    public boolean doesntExist() {
        return !exists();
    }

    public boolean exists() {
        File file = getFile();
        return !file.exists();
    }


    @SneakyThrows
    public void createFile() {
        File file = getFile();
        ManagedFile parent = getParent();
        while (true) {
            parent.createDirectoryIfNotExist();
            ManagedFile newParent = parent.getParent();
            if (newParent.equals(parent)) break;
            parent = newParent;
        }
        file.createNewFile();
    }

    @SneakyThrows
    public void createDirectoryIfNotExist() {
        File file = getFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}