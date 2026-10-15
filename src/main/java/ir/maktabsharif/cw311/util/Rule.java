package ir.maktabsharif.cw311.util;

import ir.maktabsharif.cw311.exception.BusinessException;

import java.util.function.Function;

public final class Rule {
    private Rule() {
    }

    public static void check (
            boolean condition,
            Function<String, ? extends BusinessException> exceptionFunction,
            String message
    ) throws BusinessException {
        if (condition) throw exceptionFunction.apply(message);
    }
}
