public class Matrix{
    final private Complex[][] numbers;
    final private int rows, columns;

    public Matrix(int rows, int columns, Complex[][] array){
        this.rows = rows;
        this.columns = columns;
        this.numbers = new Complex[rows][columns];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                this.numbers[i][j] = new Complex(array[i][j].get_r(), array[i][j].get_i());
            }
        }
    }

    public int get_rows(){ return this.rows; }
    public int get_columns(){ return this.columns; }
    public Complex get_elem(int i, int j){ return this.numbers[i][j]; }

    public Matrix add(Matrix m){
        if (this.rows != m.rows){
            throw new IllegalArgumentException("Error occurred: Rows of matrices do not match!");
        }
        if (this.columns != m.columns){
            throw new IllegalArgumentException("Error occurred: Columns of matrices do not match!");
        }

        Matrix res = new Matrix(m.rows, m.columns, m.numbers);
        for (int i = 0; i < m.rows; i++){
            for (int j = 0; j < m.columns; j++){
                res.numbers[i][j] = this.numbers[i][j].plus(m.numbers[i][j]);
            }
        }
        return res;
    }

    public Matrix subtract(Matrix m){
        if (this.rows != m.rows){
            throw new IllegalArgumentException("Error occurred: Rows of matrices do not match!");
        }
        if (this.columns != m.columns){
            throw new IllegalArgumentException("Error occurred: Columns of matrices do not match!");
        }

        Matrix res = new Matrix(m.rows, m.columns, m.numbers);
        for (int i = 0; i < m.rows; i++){
            for (int j = 0; j < m.columns; j++){
                res.numbers[i][j] = this.numbers[i][j].minus(m.numbers[i][j]);
            }
        }
        return res;
    }

    public Matrix multilpy(Matrix c){
        if (this.columns != c.rows) {
            throw new IllegalArgumentException("Error occurred: Such matrices cannot be multiplied: wrong number of rows and columns!");
        }

        Complex[][] numbers = new Complex[this.get_rows()][c.get_columns()];
        for (int i = 0; i < this.get_rows(); i++){
            for (int j = 0; j < this.get_columns(); j++){
                numbers[i][j] = new Complex(0,0);
            }
        }
        int index = 0;
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < c.columns; j++){
                for (int k = 0; k < this.columns; k++){
                    numbers[i][j] = numbers[i][j].plus(   this.get_elem(i, k).multiply(c.get_elem(k, j))   );
                }
                if (j == c.rows-1){ index++; }
            }

        }
        return new Matrix(this.rows, c.columns, numbers);
    }

    public Complex determinant() {
        if (this.columns != this.rows) {
            throw new IllegalArgumentException("Error occurred: only square matrices have determinant!");
        }
        if (this.columns == 1) {
            return this.numbers[0][0];
        }

        Complex res = new Complex();

        for (int i = 0; i < this.columns; i++){
            Complex[][] array = new Complex[this.rows-1][this.columns-1];
            int index = 0;
            for (int a = 1; a < this.rows; a++){
                for (int b = 0; b < this.columns; b++){
                    if (b != i){
                        array[a-1][index++] = this.numbers[a][b];
                    }
                }
                index = 0;
            }
            Matrix temp = new Matrix(this.rows-1, this.columns-1, array);

            if (i % 2 == 0) {
                res = res.plus(this.numbers[0][i].multiply(temp.determinant()));
            }
            else {
                res = res.minus(this.numbers[0][i].multiply(temp.determinant()));
            }
        }

        return res;
    }

    public Matrix transponate(){
        Complex[][] array = new Complex[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                array[j][i] = this.numbers[i][j];
            }
        }
        return new Matrix(this.rows, this.columns, array);
    }

    public void multiply_by_numb(Complex c){
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                this.numbers[i][j] = this.numbers[i][j].multiply(c);
            }
        }
    }

    public Matrix reverse(){
        if (this.columns != this.rows) {
            throw new IllegalArgumentException("Error occurred: no reversed matrix exists for non-square matrix!");
        }

        Complex det = this.determinant();
        if (det.get_r() == 0 && det.get_i() == 0) {
            throw new IllegalArgumentException("Error occurred: no reverse matrix since det = 0!");
        }
        det = new Complex(1,0).divide(det);

        Complex[][] array = new Complex[this.get_rows()][this.get_columns()];

        for (int i = 0; i <this.get_rows(); i++){
            for (int j = 0; j < this.get_columns(); j++){

                Complex[][] temp = new Complex[this.get_rows() - 1][this.get_columns() - 1];
                int index1 = 0, index2 = 0;

                for (int a = 0; a < this.get_rows(); a++){
                    for (int b = 0; b < this.get_columns(); b++){
                        if (a != i && b != j){
                            //System.out.print("\n" + index1 + " - " + index2);
                            temp[index1][index2++] = this.numbers[a][b];
                        }
                    }
                    if (a != i) {
                        index1++;
                    }
                    index2 = 0;
                }

                Matrix temp_matrix = new Matrix(this.get_rows() - 1, this.get_columns() - 1, temp);
                array[i][j] = temp_matrix.determinant();
                if ((i+j) % 2 == 1){
                    array[i][j] = array[i][j].multiply(new Complex(-1,0));
                }
            }
        }

        Matrix Alg_additions = new Matrix(this.get_rows(), this.get_columns(), array);
        Alg_additions = Alg_additions.transponate();
        Alg_additions.multiply_by_numb(det);

        return Alg_additions;
    }

    public void print(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                this.numbers[i][j].print();
                int len = 0;
                if (numbers[i][j].get_r() != 0) {len += String.format("%.2f", numbers[i][j].get_r()).length(); }
                if (numbers[i][j].get_i() < 0) {len += String.format("%.2f", numbers[i][j].get_i()).length() ;}
                if (numbers[i][j].get_i() > 0) {len += String.format("%.2f", numbers[i][j].get_i()).length()+1 ;}
                if (len > 5) {len += 3;}

                for (int k = len; k < 20; k++) {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
