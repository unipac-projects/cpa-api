package br.com.unipac.cpa.util;

import br.com.unipac.cpa.exception.NotImplementationConstructionException;
import com.google.gson.Gson;

public class JsonUtil {

    private JsonUtil(){
        throw new NotImplementationConstructionException("Classe não pode ser instanciada");
    }

    public static String getJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
