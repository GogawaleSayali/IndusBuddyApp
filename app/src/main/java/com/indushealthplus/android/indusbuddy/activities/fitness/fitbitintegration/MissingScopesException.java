package com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration;

import java.util.Collection;

/**
 * Created by amolr on 24/4/18.
 */

public class MissingScopesException extends FitbitAPIException {

    private Collection<Scope> scopes;

    public MissingScopesException(Collection<Scope> scopes) {
        this.scopes = scopes;
    }

    public Collection<Scope> getScopes() {
        return scopes;
    }
}
