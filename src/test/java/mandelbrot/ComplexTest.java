package mandelbrot;

import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1,1);
    private final Complex minusI = new Complex(0,-1);
    private final Complex minusOne = new Complex(-1,0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0,2);
    private final Complex two = new Complex(2,0);
    private final double real = -12;
    private final double imaginary = 10;


    @Test
    void testConstructor(){
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal(){
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1,1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary(){
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne(){
        assertEquals(1., Complex.ONE.getReal());
        assertEquals(0., Complex.ONE.getImaginary());
    }

    @Test
    void testI(){
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero(){
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testNegate(){
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real,-imaginary).negate());
    }

    @Test
    void testReciprocal(){
        assertEquals(Complex.ONE, Complex.ONE.reciprocal());
        assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5,0), two.reciprocal());
        assertEquals(new Complex(0.5,0.5), oneMinusI.reciprocal());
    }

    @Test
    void testReciprocalOfZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ZERO.reciprocal());
    }


    @Test
    void testSubstract(){
        assertEquals(minusOne, Complex.ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real-1,imaginary-1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide(){
        assertEquals(onePlusI, onePlusI.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), Complex.ONE.divide(two));
        assertEquals(minusI,oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ONE.divide(Complex.ZERO));
    }

    @Test
    void testConjugate(){
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testRotation(){
        assertEquals(Complex.I, Complex.rotation(Math.PI/2));
        assertEquals(minusI, Complex.rotation(-Math.PI/2));
        assertEquals(Complex.ONE, Complex.rotation(0));
        assertEquals(new Complex(sqrt(2)/2., sqrt(2)/2.),
                Complex.rotation(Math.PI/4));
        assertEquals(new Complex(1./2., sqrt(3)/2.),
                Complex.rotation(Math.PI/3));
    }

    @Test
    void testToString(){
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real="+real+", imaginary="+imaginary+"}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testEquals(){
        /**On test si deux meme complexes sont bien égaux (c1=c2)*/
        Complex c1 = new Complex(1,1);
        Complex c2 = new Complex(1,1);
        assertEquals(c2.equals(c1),true);

        /**On test si equals nous retourne "false" si l'objet "o" dans c2.equals(o) est null*/
        Complex c3= null;
        assertEquals(c2.equals(c3),false);

        /**On test si equals reconait bien la différence de type et renvoie false*/
        int c4=1;
        assertFalse(c2.equals(c4));

        /**On test si deux complexes ont un chiffre réel différent*/
        Complex c5 = new Complex(2,1);
        assertEquals(c2.equals(c5),false);

        /**On test si deux complexes ont un chiffre imaginaire différent*/
        Complex c6 = new Complex(1,2);
        assertEquals(c2.equals(c6),false);

        }

    @Test
    void testsquareModulus(){
        Complex c1= new Complex( 3,8);
        assertEquals( c1.squaredModulus(),3 * 3 + 8 * 8);
    }


    @Test
    void testStaticComplexReal(){
        Complex c1= new Complex( 3,8);
        Complex c2= new Complex( 3,0);
        assertEquals(c1.real(c1.real), c2);
    }

    @Test
    void testAdd(){
        Complex c1= new Complex( 3,8);
        Complex c2= new Complex( 6,16);
        assertEquals(c1.add(c1),c2);
        Complex c3= new Complex( 7,16);
        assertNotEquals(c1.add(c1),c3);
    }

    @Test
    void testMultiply(){
        Complex c1= new Complex( 4,2);
        Complex c2= new Complex( 12,16);
        assertEquals(c1.multiply(c1),c2);
        assertNotEquals(c1.add(c1),c1);
    }

    @Test
    void testsubstract(){
        Complex c1= new Complex( 3,8);
        Complex c2= new Complex( 6,16);
        assertEquals(c2.subtract(c1),c1);
        Complex c3= new Complex( 7,16);
        assertNotEquals(c2.add(c1),c2);
    }


    @Test
    void testModulus(){
        Complex c1=new Complex(3,1);
        assertEquals(c1.modulus(),sqrt(10));
    }

    @Test
    void testPow(){
        int p1 =3;
        Complex c1=new Complex(2,3);
        int p2 =0;
        Complex c2=new Complex((8-3*18),(3*2*2*3-3*3*3));
        assertEquals( c1.pow(p1), c2);
        Complex c3=new Complex(0, 0);
        assertEquals(c1.pow(p2), c3);
    }

    @Test
    void testScale(double lambda){}

    @Test
    void testBooleanEquals(Object o){}

}