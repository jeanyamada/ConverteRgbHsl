/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converteCores;

import sistemaCores.*;

/**
 *
 * @author jeany
 */
public class ConvertColor {

    public static Hsl rgbToHsl(Rgb rgb) {

        Double min, max,delta;

        Hsl rgbAux = new Hsl(rgb.getR() /255.0, rgb.getG() /255.0, rgb.getB() /255.0);

        min = Math.min(rgbAux.getH(),Math.min(rgbAux.getL(),rgbAux.getS()));       

        max = Math.max(rgbAux.getH(),Math.max(rgbAux.getL(), rgbAux.getS()));
        
        delta = max - min;
        
        Hsl hsl = new Hsl();
        
        hsl.setL(LightnessCalculation(max,min)*240.0d);
        
        hsl.setS(SaturationCalculation(delta,LightnessCalculation(max,min))*240.0d);
        
        hsl.setH(HueCalculation(rgbAux,delta,max)*240.0d);       

        return hsl;

    }
    
    public static Rgb hslToRgb(Hsl hsl){
        
        Hsl hslAux = new Hsl(hsl.getH()/240.0d,hsl.getS()/240.0d,hsl.getL()/240.0d);
        
        double c  = (1.0d - Math.abs(2.0d*hslAux.getL() - 1.0d)) * hslAux.getS();
        double x = c * (1.0d - Math.abs((hslAux.getH()/ (1.0d/6.0d)) % 2.0d - 1.0d));
        double m = hslAux.getL() - c/2.0d;
        
        
        Hsl rgbAux = new Hsl();
        
        if(hslAux.getH() >= 0.0d && hslAux.getH() < (1.0d/6.0d)){
            rgbAux.setH(c);
            rgbAux.setS(x);
            rgbAux.setL(0.0d);
        }
        else if(hslAux.getH() >= (1.0d/6.0d) && hslAux.getH() < (1.0f/3.0f)){
            rgbAux.setH(x);
            rgbAux.setS(c);
            rgbAux.setL(0.0d);
        }
        else if(hslAux.getH() >= (1.0f/3.0f) && hslAux.getH() < (1.0f/2.0f)){
            rgbAux.setH(0.0d);
            rgbAux.setS(c);
            rgbAux.setL(x);
        }
        else if(hslAux.getH() >= (1.0f/2.0f) && hslAux.getH() < (2.0f/3.0f)){
            rgbAux.setH(0.0d);
            rgbAux.setS(x);
            rgbAux.setL(c);
        }
        else if(hslAux.getH() >= (2.0f/3.0f) && hslAux.getH() < (5.0f/6.0f)){
            rgbAux.setH(x);
            rgbAux.setS(0.0d);
            rgbAux.setL(c);
        }
        else if(hslAux.getH() >= (5.0f/6.0f) && hslAux.getH() < (1.0d)){
            rgbAux.setH(c);
            rgbAux.setS(0.0d);
            rgbAux.setL(x);
        }
        
    
        Rgb rgb = new Rgb();
        
        rgb.setR((int)Math.round((rgbAux.getH()+m)*255.0d));
        rgb.setG((int)Math.round((rgbAux.getS()+m)*255.0d));
        rgb.setB((int)Math.round((rgbAux.getL()+m)*255.0d));
        
        return rgb;
    }
    private static Double HueCalculation(Hsl hsl,Double delta,Double max){
        
        if(delta == 0.0f) return 0.0d;
        
        Double h;        
        
        if(hsl.getH().equals(max)){
            h = (hsl.getS() - hsl.getL())/delta;
            h = h%6;
        }        
        else if(hsl.getS().equals(max)){
            h = (hsl.getL() - hsl.getH())/delta;
            h = h+2;
        }
        else{
            h = (hsl.getH() - hsl.getS())/delta;
            h = h+4;
        }
        
        return h/6.0d;
        
    }
    
    private static Double SaturationCalculation(double delta,double l){
        if(delta == 0) return 0.0d;
        
        return delta/(1.0d - Math.abs(2.0d * l - 1.0d));
    }
    
    private static Double LightnessCalculation(double max,double min){
        return (max+min)/2;
    }
}
