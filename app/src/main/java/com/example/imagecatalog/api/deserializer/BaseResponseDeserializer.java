package com.example.imagecatalog.api.deserializer;

import com.example.imagecatalog.api.response.BaseResponse;
import com.example.imagecatalog.model.dataclass.Data;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BaseResponseDeserializer implements JsonDeserializer<BaseResponse> {

    @Override
    public BaseResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess(json.getAsJsonObject().get("success").getAsBoolean());
        baseResponse.setStatus(json.getAsJsonObject().get("status").getAsInt());

        JsonElement dataJsonElement = json.getAsJsonObject().get("data");
        Gson gson = new Gson();
        if (dataJsonElement.isJsonObject()) {
            baseResponse.setDataObject(gson.fromJson(dataJsonElement.toString(), Data.class));
        } else {
            Type dataListType = new TypeToken<ArrayList<Data>>() {
            }.getType();
            ArrayList<Data> dataArray = gson.fromJson(dataJsonElement.toString(), dataListType);
            baseResponse.setDataArray(dataArray);
        }
        return baseResponse;
    }
}
