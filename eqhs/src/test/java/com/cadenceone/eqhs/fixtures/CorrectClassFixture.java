package com.cadenceone.eqhs.fixtures;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public abstract class CorrectClassFixture {


    public abstract Object getField() ;
    public abstract int getHashCodeBuilderRandomSeedOne() ;
    public abstract int getHashCodeBuilderRandomSeedTwo() ;

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        CorrectClassFixture rhs = (CorrectClassFixture) obj;
        return new EqualsBuilder().append(getField(), rhs.getField()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(getHashCodeBuilderRandomSeedOne(), getHashCodeBuilderRandomSeedTwo()).append(getField()).toHashCode();
    }
}
