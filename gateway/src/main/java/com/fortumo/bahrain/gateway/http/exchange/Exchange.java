package com.fortumo.bahrain.gateway.http.exchange;

public class Exchange {

    public interface BodyImpl extends ContentTypeSenders {};
    private static final BodyImpl BODY = new BodyImpl(){};
    public static BodyImpl body() {
        return BODY;
    }

    public interface QueryParamImpl extends QueryParams {};
    private static final QueryParamImpl QUERYPARAMS = new QueryParamImpl(){};
    public static QueryParamImpl queryParams() {
        return QUERYPARAMS;
    }

}
