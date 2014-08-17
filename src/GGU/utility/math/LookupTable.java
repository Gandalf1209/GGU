package GGU.utility.math;

public class LookupTable {

    double tau;
    int precision;

    double[] sin, cos, tan, alpha;

    public LookupTable(int precision){
        this.precision = precision;
        this.tau = Math.PI * 2;
        generateTable(precision);
    }
    public void generateTable(int precision){
        sin = new double[precision];
        cos = new double[precision];
        tan = new double[precision];

        for(int i = 0; i < precision; i++){
            double theta = (((double)i) / ((double)precision)) * tau;

            sin[i] = Math.sin(theta);
            cos[i] = Math.cos(theta);
            tan[i] = Math.tan(theta);
        }

        alpha = new double[256];
        for(int i = 0; i < 256; i++){
            alpha[i] = (((double)i) / 255);
        }
    }
    public double sin(double theta){
        return sin[index(theta)];
    }
    public double cos(double theta){
        return cos[index(theta)];
    }
    public double tan(double theta){
        return tan[index(theta)];
    }
    public double alpha(int index) { return alpha[index]; }
    public int index(double theta){
        double precision = this.precision;
        int index = (int)((theta * precision) / tau) % this.precision;
        if(index < 0){
            index = this.precision + index;
        }
        return (index);
    }

}
