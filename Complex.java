public class Complex{
    private double real;
    private double imag;

    public Complex(double real, double imag){
        this.real = real;
        this.imag = imag;
    }

    public Complex(){
        this.real = 0;
        this.imag = 0;
    }

    public void set_r(int numb){
        this.real = numb;
    }
    public void set_i(int numb) {
        this.imag = numb;
    }

    public double get_r(){ return this.real; }
    public double get_i(){ return this.imag; }

    public Complex plus(Complex c){
        return new Complex(this.real + c.real, this.imag + c.imag);
    }
    public Complex minus(Complex c){
        return new Complex(this.real - c.real, this.imag - c.imag);
    }

    public Complex multiply(Complex c){
        return new Complex(this.real*c.real - this.imag*c.imag, this.real*c.imag + this.imag*c.real);
    }
    public Complex divide(Complex c){
        if (c.real == 0 && c.imag == 0){
            throw new ArithmeticException("Error occurred: division by 0 is impossible!\n");  //исключение обрабатывается в Main
        }
        return new Complex((this.real*c.real + this.imag*c.imag)/(c.real*c.real + c.imag*c.imag),
                          (this.imag*c.real - this.real*c.imag)/(c.real*c.real + c.imag*c.imag));
    }

    public void print(){
        if (this.real == 0 & this.imag == 0) {System.out.print(0);}
        else if (this.real == 0) {System.out.printf("%.2fi", this.imag);}
        else if (this.imag == 0) {System.out.printf("%.2f", this.real);}
        else if (this.imag > 0) System.out.printf("%.2f + %.2fi", this.real, this.imag);
        else if (this.imag < 0) System.out.printf("%.2f - %.2fi", this.real, -this.imag);
    }
}
