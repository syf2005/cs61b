public class Planet {
    private static double G_constant = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet another){
        double r_square = (this.xxPos-another.xxPos)*(this.xxPos-another.xxPos)+(this.yyPos-another.yyPos)*(this.yyPos-another.yyPos);
        return Math.sqrt(r_square);
    }

    public double calcForceExertedBy(Planet another){
        double distance = calcDistance(another);
        return G_constant*this.mass*another.mass/distance/distance;
    }

    public double calcForceExertedByX(Planet another){
        double x_distance = another.xxPos - this.xxPos;
        double distance = calcDistance(another);
        double force = calcForceExertedBy(another);
        return force*x_distance/distance;
    }

    public double calcForceExertedByY(Planet another){
        double y_distance = another.yyPos - this.yyPos;
        double distance = calcDistance(another);
        double force = calcForceExertedBy(another);
        return force*y_distance/distance;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double result = 0;
        for (Planet element:allPlanets){
            if (element == this){
                continue;
            }
            result += calcForceExertedByX(element);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double result = 0;
        for (Planet element:allPlanets){
            if (element == this){
                continue;
            }
            result += calcForceExertedByY(element);
        }
        return result;
    }

    public void update(double time, double x_force, double y_force){
        double x_acce = x_force/mass;
        double y_acce = y_force/mass;
        xxVel += x_acce * time;
        yyVel += y_acce * time;
        xxPos += xxVel * time;
        yyPos += yyVel * time;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
