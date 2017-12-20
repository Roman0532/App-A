package config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provider;

public class SerializeProvider implements Provider<Gson> {
    public Gson get() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}
