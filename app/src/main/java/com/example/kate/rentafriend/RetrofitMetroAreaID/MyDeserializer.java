package com.example.kate.rentafriend.RetrofitMetroAreaID;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

class MyDeserializer implements JsonDeserializer<MetroAreaID>
{
    @Override
    public MetroAreaID deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        // Get the "content" element from the parsed JSON
        JsonElement id = je.getAsJsonObject().get("id");

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return new Gson().fromJson(id, MetroAreaID.class);

    }
}