package com.cadenceone.eqhs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker<T> {

    MultipleEqualsAndHashCodeContractChecker shouldBeEqualChecker;
    MultipleEqualsAndHashCodeContractChecker shouldBeDifferentChecker;

    boolean[] sameBooleanArray = new boolean[1];
    boolean[] diffBooleanArray = new boolean[1];
    int[] sameIntArray = new int[1];
    int[] diffIntArray = new int[1];
    byte[] sameByteArray = new byte[1];
    byte[] diffByteArray = new byte[1];
    char[] sameCharArray = new char[1];
    char[] diffCharArray = new char[1];
    double[] sameDoubleArray = new double[1];
    double[] diffDoubleArray = new double[1];
    float[] sameFloatArray = new float[1];
    float[] diffFloatArray = new float[1];
    long[] sameLongArray = new long[1];
    long[] diffLongArray = new long[1];
    short[] sameShortArray = new short[1];
    short[] diffShortArray = new short[1];
    String[] sameStringArray = new String[1];
    String[] diffStringArray = new String[1];
    Object[] sameObjectArray = new Object[1];
    Object[] diffObjectArray = new Object[1];

    {

        sameBooleanArray[0] = true;
        diffBooleanArray[0] = false;
        sameIntArray[0] = 1;
        diffIntArray[0] = 2;
        sameByteArray[0] = (byte)5;
        diffByteArray[0] = (byte)6;
        sameCharArray[0] = 'a';
        diffCharArray[0] = 'b';
        sameDoubleArray[0] = (double)7.0;
        diffDoubleArray[0] = (double)8.0;
        sameFloatArray[0] = (float)9.0;
        diffFloatArray[0] =  (float)10.0;
        sameLongArray[0] = (long)11;
        diffLongArray[0] =  (long)12;
        sameShortArray[0] = (short)3;
        diffShortArray[0] =  (short)4;
        sameStringArray[0] =  "c";
        diffStringArray[0] =  "d";
        sameObjectArray[0] = new Object();
        diffObjectArray[0] =  new Object();
    }

    //values 1 and 2 are used for int, long, double and float.
    List<?> sameValues = Arrays.asList(true, 1, 'a',(short)3, (byte)5,  "c",sameBooleanArray, sameIntArray, sameByteArray, sameCharArray, sameDoubleArray, sameFloatArray, sameLongArray, sameShortArray, sameStringArray, sameObjectArray);
    List<?> diffValues = Arrays.asList(false, 2, 'b', (short)4, (byte)6, "d", diffBooleanArray, diffIntArray, diffByteArray, diffCharArray, diffDoubleArray, diffFloatArray, diffLongArray, diffShortArray, diffStringArray, diffObjectArray);

    public ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(Class<T> classUnderTest, String mustSpecifyAtLeastOneFieldName) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T  firstObject =  createObject(classUnderTest);
        firstObject = setFieldSame(firstObject, mustSpecifyAtLeastOneFieldName);

        T  equalObject =  createObject(classUnderTest);
        equalObject = setFieldSame(equalObject, mustSpecifyAtLeastOneFieldName);

        T  differentObject =  createObject(classUnderTest);
        differentObject = setFieldDifferent(differentObject, mustSpecifyAtLeastOneFieldName);

        shouldBeEqualChecker = new MultipleEqualsAndHashCodeContractChecker(firstObject, equalObject);
        shouldBeDifferentChecker  = new MultipleEqualsAndHashCodeContractChecker(firstObject, differentObject);

    }

    public boolean implementsEqualsCorrectly() {
        return shouldBeEqualChecker.allAreSameByEqualsContract() && shouldBeDifferentChecker.allAreDifferentByEqualsContract();
    }


    private T createObject(Class<T> classUnderTest) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Constructor constructor = getNoArgumentConstructor(classUnderTest);
	    constructor.setAccessible(true);
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


    private T setFieldSame(T object, String field) throws NoSuchFieldException, IllegalAccessException {
        return tryToSet(object, field, sameValues);
    }

    private T setFieldDifferent(T object, String field) throws NoSuchFieldException, IllegalAccessException {
        return tryToSet(object, field, diffValues);
    }

    private T tryToSet(T object, String field, List<?> valuesToTry) throws NoSuchFieldException, IllegalAccessException {
        Field f = object.getClass().getDeclaredField(field);
	    f.setAccessible(true);
        for (Object value: valuesToTry){
            try {
                f.set(object, value);
                System.out.println("Successfully set to "+value);
                return object;
            }catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                if (iae.getMessage().contains("Can not set") && (iae.getMessage().contains("to java.lang") || iae.getMessage().contains("to ["))){
                    continue;
                }
                throw iae;
            }
        }
        throw new RuntimeException("Could not set field '" +field+"'");
    }
}
