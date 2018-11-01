import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;

public class KNN {
	
	private Point center ;
	private ArrayList<Point> dataSet ;
	private double rayon;
	private int k ;
	private ArrayList<Point> testSet;
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public double getRayon() {
		return rayon;
	}
	
	public void setRayon(float rayon) {
		this.rayon = rayon;
	}
	
   public KNN(Point center ,double ray,int k ,int trainingCount) {
	   this.center = center;
	   this.rayon = ray;
	   this.k = k;
	   this.dataSet = new ArrayList<Point>();
	   this.generateTheDataSet( trainingCount);   
   }
  
   private double distance(Point a , Point b) {
	   // this is to calculate the distance between two points a and b 
	   return Math.sqrt(Math.pow((a.x -b.x),2.0)+Math.pow((a.y - b.y),2.0));
   }

   private void generateTheDataSet(int count) {
	   //50% class A inside the circle  and 50% class B 
	   System.out.println("generating the trainning  dataset.....");
	   double a=0,b = 0;
	   Point m = new Point(a,b);
	   Random r ;
	   int i = 0;
	   double distance = this.distance(center, m) ;

	   do {
		  r = new Random();
		  a = this.center.x +r.nextDouble();
		  b = this.center.x +r.nextDouble();
		  m = new Point(a,b);
		  distance = this.distance(this.center, m);
		  
		  if(distance <= this.rayon) { // only if the point is in the circle we then add it to the class A
			  m.setPointClass("A");
			  this.dataSet.add(m);
			   i++;
		  }
		   
	   }while(i< count/2);
	   // now we will be generating the 50% that are out of our decision boundary ( class B)
	   
	   do {
			  r = new Random();
			  a = this.rayon+r.nextDouble();
			  b = this.rayon +r.nextDouble();
			  m = new Point(a,b);
			  distance = this.distance(this.center, m);
			  
			  if(distance > this.rayon) { // if we are generating points that suppose to be in class B
				  m.setPointClass("B");
				  this.dataSet.add(m);
				   i++;
			  }
			   
		   }while(i< count);
	   System.out.println(" trainning  dataset generated :D ");
   }
   
   public Point selectedClass(Point point){
	   for(int j = 0; j < this.dataSet.size() ; j++) {
		   this.dataSet.get(j).setDistanceFromCenter(this.distance(this.dataSet.get(j), point));
	   }
	  Collections.sort(this.dataSet);
	 
	  int classA = 0, classB = 0;
	  //System.out.println("classes of the "+this.getK()+" neighbours are ");
	  for (int i = 0; i <this.k ; i++) {
		  
		  //System.out.println(this.dataSet.get(i).getPointClass());
		  if(this.dataSet.get(i).getPointClass() == "A")
			 classA++;
		  else classB++;	  
	  }
	  if(classA > classB)
		  point.setPointClass("A");
	  else 
		  point.setPointClass("B");
	 return point;  
   }
   
   public ArrayList<Point> getDataSet() {
	return dataSet;
}

public void setDataSet(ArrayList<Point> dataSet) {
	this.dataSet = dataSet;
}

public int getK() {
	return k;
}

public void setK(int k) {
	this.k = k;
}

public void setRayon(double rayon) {
	this.rayon = rayon;
}

public double error(ArrayList<Point> testSet){
	   double result = 0;
	   //first we calculate the sum of wrong results
	   for(int i = 0; i < testSet.size() ; i ++) {
		   Point p = new Point(testSet.get(i).x,testSet.get(i).y);
		   p = this.selectedClass(p);
		   if(p.getPointClass() != testSet.get(i).pointClass)
			   result += 1;
	   }
	   return (result / testSet.size())*100;
   }
	public ArrayList<Point> introduceNoise(double noisePortion){
		double noiseCount = this.dataSet.size()*noisePortion;
		/* to maintain a true random noise without duplicating the effect on one element only 
		 * i will be using a LinkedHashSet just to pick randomly the training set examples 
		 * that are going to be affected by the noise 
		 */
		ArrayList<Point> result = new ArrayList<Point>();
		result = (ArrayList<Point>)this.dataSet.clone();
		int max = this.dataSet.size();
		LinkedHashSet<Integer> noisedElements = new LinkedHashSet<Integer>();
		Random r = new Random();
		while(noisedElements.size() < noiseCount) {
			Integer a = r.nextInt(max) + 1;
		    // As we're adding to a set, this will automatically do a containment check
		    noisedElements.add(a);
		}
		System.out.println(noisedElements);
		/* now let's affect those elements*/
		for( Integer e :noisedElements) {
			if (result.get(e).getPointClass() == "A")
				result.get(e).setPointClass("B");
			else result.get(e).setPointClass("A");
		}
		return result;
	}
}
