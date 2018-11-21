package me.bristermitten.tribal.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.bristermitten.tribal.Tribal;

public class TribalBinderModule extends AbstractModule {

    private final Tribal tribalPlugin;

    public TribalBinderModule(Tribal tribalPlugin) {
        this.tribalPlugin = tribalPlugin;
    }

    @Override
    protected void configure() {
        this.bind(Tribal.class).toInstance(tribalPlugin);
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }
}
