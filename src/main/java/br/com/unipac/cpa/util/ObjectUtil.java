package br.com.unipac.cpa.util;

import br.com.unipac.cpa.exception.NotImplementationConstructionException;

import java.util.Objects;

public class ObjectUtil {

    private ObjectUtil(){
        throw new NotImplementationConstructionException("Classe não pode ser instanciada");
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().trim().isEmpty();
    }

    public static void isNull(Object obj) {
        Objects.requireNonNull(obj, "obj must not be null");
    }
}
