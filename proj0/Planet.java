public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((p.xxPos-xxPos)*(p.xxPos-xxPos) + (p.yyPos-yyPos)*(p.yyPos-yyPos));
    }
    public double calcForceExertedBy(Planet p){
        return G*mass*p.mass/Math.pow(calcDistance(p),2);
    }
    public double calcForceExertedByX(Planet p){
        return (p.xxPos-xxPos)*calcForceExertedBy(p)/calcDistance(p);
    }
    public double calcForceExertedByY(Planet p) {
        return (p.yyPos-yyPos)*calcForceExertedBy(p)/calcDistance(p);

    }
    public double calcNetForceExertedByX(Planet[] p){
        double res = 0;
        for(Planet x:p){
            if(x.equals(this)){
                continue;
            }
            res +=calcForceExertedByX(x);
        }
        return res;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double res = 0;
        for(Planet xx:p){
            if(xx.equals(this)){
                continue;
            }
            res += calcForceExertedByY(xx);
        }
        return res;
    }
    public void update(double dt,double fx,double fy){
        double ax = fx/mass;
        double ay = fy/mass;
        xxVel += dt*ax;
        yyVel += dt*ay;
        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }

}