import java.util.ArrayList;

/**
 * 
 */

/**
 * @author soumia
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// the following is the test scenario for the first version 
		Point center = new Point(0.0,0.0);
		double rayon = 5.2;
		int k = 3, trainingSize = 20;
		System.out.println("first version test ");
		KNN algo = new KNN(center,rayon,k,trainingSize);

		Point p = new Point(2.0,8.0); 
		System.out.println("p=("+p.x+" , "+p.y+" )");
		System.out.println("in the process of choosing the appropriate class ....");
		p = algo.selectedClass(p);
		System.out.println("the selected class is "+p.getPointClass());
		
	    ArrayList<Point> testSet = new ArrayList<Point>();
	    /* Now generally speaking we divide our dataSet into 60% for training and 40% for testing
	     * and in some advanced problems we get to divide into 60% for training  and 20% for both cross validation 
	     * and another 20% for testing 
	     * here and because we have 20 points for training so we will be needing 13 points for testing*/
	    testSet.add(new Point(2.0,4.0,"A"));
	    testSet.add( new Point(3.0,4.0,"A"));
	    testSet.add(new Point(12.0,4.0,"B"));
	    testSet.add(new Point(18.0,6.0,"B"));
	    testSet.add(new Point(2.2,5.0,"B"));
	    testSet.add(new Point(3.32,7.23,"B"));
	    testSet.add(new Point(1.0,1.0,"A"));
	    testSet.add(new Point(0.0,5.0,"A"));
	    testSet.add(new Point(5.3,0.0,"B"));
	    testSet.add(new Point(2.2,16.0,"B"));
	    testSet.add( new Point(4.2,1,"A"));
	    testSet.add( new Point(4.0,5.0,"B"));
	    testSet.add(new Point(2.0,3.0,"A"));
	    
	    System.out.println("the error = " +String.format("%.2f", algo.error(testSet))+" % \n ");
	    System.out.println("-----------explanation -------------------------------------------------------------------------------------------------------");
	    System.out.println("the error is considerable( 15% in some executions is a bit to look at with an open mind) due to the following : "); 
	    System.out.println("1- in the trainning set the data distribution is not random 100%( it's a pseudo-random generation algorithm, ( source : javaDoc) ");
	    System.out.println("2- in my trainning set most of my points are in average 1 distance unit away from the border");
	    System.out.println("3- while in my trainning set i took some tricky points that are so close to the border i did \n \t this in the seek of making my trainning set more representative ");
	    System.out.println(" ===> this why we get this error value" );
	    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	    System.out.println("now version 2 and introducing a noise .........");
	    /*5% in our training set (20 points )corresponds to  1 example 
	     * so i will be using 20% which is 4 training examples */
	    
	    ArrayList<Point> noisedTrainingSet = algo.introduceNoise(0.2);
	    KNN algoNoised = new KNN(center,rayon,k,trainingSize);
	    algoNoised.setDataSet(noisedTrainingSet);
	    System.out.println("the error = " +String.format("%.2f", algoNoised.error(testSet))+" % \n ");

	}

}
