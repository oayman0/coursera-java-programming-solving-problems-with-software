import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count =0;
        for(Point p : s.getPoints()){
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            double side = prevPt.distance(currPt);
            prevPt = currPt;
            if(side > largestSide){
                largestSide = side;
            }
            
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double x = s.getLastPoint().getX();
        for (Point currPt : s.getPoints()){
            if(currPt.getX()>x){
                x = currPt.getX();
            }
        }
        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(perimeter > largestPerim){
                largestPerim = perimeter;
            }
         }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0;
        File currFile = null;  
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(perimeter > largestPerim){
                largestPerim = perimeter;
                currFile = f;
            }
         }
         
          // replace this code
        return currFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
         System.out.println("perimeter = " + length);
        System.out.println("perimeter = " + String.format("%.2f", length));
        //getNumPoints method
        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);
        //getAverageLength
        double avgLength = getAverageLength(s);
         System.out.println("Avg side length = " + avgLength);
        System.out.println("Avg side length = " +String.format("%.2f", avgLength));
        //getLargestSide
        double largestSide = getLargestSide(s);
        System.out.println("largest Side = " + largestSide);
        System.out.println("largest Side = " +String.format("%.2f", largestSide));
        //getLargestX
        double largestX = getLargestX(s);
         System.out.println("largest x = " + largestX);
        System.out.println("largest x = " +String.format("%.2f", largestX));
        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("largest Perim of them = " +String.format("%.2f", largestPerim));
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String name = getFileWithLargestPerimeter();
        System.out.println("File with largest Perim = " + name);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
