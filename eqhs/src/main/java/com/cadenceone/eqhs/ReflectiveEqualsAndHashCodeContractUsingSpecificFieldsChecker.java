package com.cadenceone.eqhs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker<T> {

    MultipleEqualsAndHashCodeContractChecker shouldBeEqualChecker;
    MultipleEqualsAndHashCodeContractChecker shouldBeDifferentChecker;

    public ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(Class<T> classUnderTest, String mustSpecifyAtLeastOneFieldName) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T  firstObject =  createObject(classUnderTest);
        firstObject = setField(firstObject, mustSpecifyAtLeastOneFieldName, true);

        T  equalObject =  createObject(classUnderTest);
        equalObject = setField(equalObject, mustSpecifyAtLeastOneFieldName, true);

        T  differentObject =  createObject(classUnderTest);
        differentObject = setField(differentObject, mustSpecifyAtLeastOneFieldName, false);

        shouldBeEqualChecker = new MultipleEqualsAndHashCodeContractChecker(firstObject, equalObject);
        shouldBeDifferentChecker  = new MultipleEqualsAndHashCodeContractChecker(firstObject, differentObject);

    }

    public boolean implementsEqualsCorrectly() {
        return shouldBeEqualChecker.allAreSameByEqualsContract() && shouldBeDifferentChecker.allAreDifferentByEqualsContract();
    }


    private T createObject(Class<T> classUnderTest) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Constructor constructor = getNoArgumentConstructor(classUnderTest);
	    constructor.setAccessible(true);
        System.out.println(constructor);
        return (T)constructor.newInstance();

    }

    private Constructor getNoArgumentConstructor(Class<T> classUnderTest) {
        Constructor[] constructors = classUnderTest.getDeclaredConstructors();
        Constructor constructor = null;
        for (int i = 0; i < constructors.length; i++) {
            constructor = constructors[i];
            if (constructor.getGenericParameterTypes().length == 0)
            break;
        }
        return constructor;
    }

    private T setField(T object, String field, boolean value) throws NoSuchFieldException, IllegalAccessException {
        Field f = object.getClass().getDeclaredField(field);
	    f.setAccessible(true);
        f.set(object, value);
        return object;
    }

}
