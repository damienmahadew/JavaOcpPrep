package co.za.damien.chapter4.streams;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleToIntFunction;

/**
 * Just as there are special streams and optional classes for primitives there are also special functional interfaces
 * you get double, long and int except for the BooleanSupplier
 */
public class FunctionalInterfacesForPrimitives {
    
    private void functionalInterfacesForBoolean() {
        /**
         * BooleanSupplier is a separate type
         * one method to implement - boolean getAsBoolean()
         */
        //eg
        BooleanSupplier b1 = () -> true;
        BooleanSupplier b2 = () -> Math.random() > .5;
    }
    
    private void functionalInterfacesForDoubleIntLong() {
        /**
         * Most of the functional interfaces are for double, int and long to match the streams and optionals
         * 
         * Functional Interfaces    # Parameters        Return Type     Single abstract method
         * DoubleSupplier           0                   double          getAsDouble
         * IntSupplier                                  int             getAsInt
         * LongSupplier                                 long            getAsLong
         * 
         * DoubleConsumer           1(double)           void            accept
         * IntConsumer              1(int)
         * LongConsumer             1(long)
         * 
         * DoublePredicate          1(double)           boolean         test
         * IntPredicate             1(int)
         * LongPredicate            1(long)
         * 
         * DoubleFunction<R>        1(double)           R               apply
         * IntFunction<R>           1(int)                              
         * LongFunction<R>          1(long)        
         * 
         * DoubleUnaryOperator      1(double)           double          applyAsDouble
         * IntUnaryOperator         1(int)              int             applyAsInt
         * LongUnaryOperator        1(long)             long            applyAsLong
         *
         * DoubleBinaryOperator     2(double, double)   double          applyAsDouble
         * IntBinaryOperator        2(int, int)         int             applyAsInt
         * LongBinaryOperator       2(long, long)       long            applyAsLong
         *
         * BiConsumer, BiPredicate and BiFunction are not available for primitives
         *
         * Primitive Specific Functional Interfaces
         *
         * ToDoubleFunction<T>      1(T)                double          applyAsDouble
         * ToIntFunction<T>                             int             applyAsInt
         * ToLongFunction<T>                            long            applyAsLong
         *
         * ToDoubleBiFunction<T, U> 2(T, U)             double          applyAsDouble
         * ToIntBiFunction<T, U>                        int             applyAsInt
         * ToLongBiFunction<T, U>                       long            applyAsLong
         *
         * DoubleToIntFunction      1(double)           int             applyAsInt
         * DoubleToLongFunction     1(double)           long            applyAsLong
         * IntToDoubleFunction      1(int)              double          applyAsDouble
         * IntToLongFunction        1(int)              long            applyAsLong
         * LongToDoubleFunction     1(long)             double          applyAsDouble
         * LongToIntFunction        1(long)             int             applyAsInt
         *
         * ObjDoubleConsumer<T>     2(T, double)        void            accept
         * ObjIntConsumer<T>        2(T, int)
         * ObjLongConsumer<T>       2(T, int)
         *
         *
         *  EXAM!!!
         */
        //e.g
        double d = 1.0;
        DoubleToIntFunction f1 = x -> 1;
        f1.applyAsInt(d);

    }
    
}
