/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaCores;

/**
 *
 * @author jeany
 */
public class Hsl {
    private Double h,s,l;

    public Hsl() {
    }

    public Hsl(Double h, Double s, Double l) {
        this.h = h;
        this.s = s;
        this.l = l;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public void setS(Double s) {
        this.s = s;
    }

    public void setL(Double l) {
        this.l = l;
    }

    public Double getH() {
        return h;
    }

    public Double getS() {
        return s;
    }

    public Double getL() {
        return l;
    }


    
}
