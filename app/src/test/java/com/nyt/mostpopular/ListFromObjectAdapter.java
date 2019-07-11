package com.nyt.mostpopular;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public final class ListFromObjectAdapter implements TypeAdapterFactory {
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (List.class != type.getRawType()) {
            throw new AssertionError("Adapter can only be set on List type.");
        }
        final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, type);

        Type collectionType = $Gson$Types.getCollectionElementType(type.getType(), type.getRawType());
        final TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(collectionType));

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegateAdapter.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.BEGIN_ARRAY) {
                    Object value = elementAdapter.read(in);
                    //noinspection unchecked
                    return (T) Collections.singletonList(value);
                } else if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    Object value = elementAdapter.read(in);
                    //noinspection unchecked
                    return (T) Collections.singletonList(value);
                }
                return delegateAdapter.read(in);
            }
        };
    }
}