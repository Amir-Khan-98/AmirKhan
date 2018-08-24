import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Lab15 {
	public static double [][] report ;

	public static void main(String args[]) throws IOException
	{
	
	
	
	//ArrayList<Integer> opt = new ArrayList<Integer>();
	//opt = TSP.ReadIntegerFile("C:\\TSP\\TSP_48_OPT.txt");
	//double sol = TSPSolution.TSPFitness(opt.size(), opt, x);
	//System.out.println(sol);
	ArrayList<Integer> solution = new ArrayList<Integer>();
	String filename = "C:\\TSP\\TSP_51.txt";
	double[][] x;
	x = TSP.ReadArrayFile(filename, " ");
	int N=  x.length;
	double tIter = 0.1;
	double t0 = 150;
	double lamda;
	int iter = 100000;
	int n =51;
	lamda = Math.pow((tIter/t0), 1/iter);
	lamda = Math.pow((0.1/150), 1/iter);
	ArrayList<Integer> perm=new ArrayList<Integer>();
	TSPSolution.RandPerm(x);
	perm=TSPSolution.getCities();
	//solution = RRHC(n,x, 10000,10);
	//solution = RMHC(n, perm,x, iter);
	
	//solution = SA(N,perm,x,iter,t0,lamda);
	//int d = solution.size();
	//System.out.println(TSPSolution.TSPFitness(solution.size(), solution, x));
	//for(int i=0;i<d;++i){
		//System.out.print(solution.get(i)+" ");
	//}
	
	/*
	double [][] mst ;
	mst = MST.PrimsMST(x);
	
	double mstL = TSPSolution.TSPFitness(d, solution, mst);
	System.out.println(mstL);
	double solF = TSPSolution.TSPFitness(solution.size(), solution, x);
	System.out.println(solF);
	double res = (mstL/solF)*100;
	System.out.println(res);
	*/
	String File = "C:\\Users\\AmirKhan\\Desktop\\Report\\Report.txt";
	
	WritingData(File, iter,perm);
	
	}
	//RMHC function takes in number of cities to visit(N), the 2D array(D)
		//the tour(t), and number of iterations and returns optimal tour route
		//as an integer array list
		public static ArrayList<Integer> RMHC(int N, ArrayList<Integer> t, double[][] D,int iter)
		{
			
			ArrayList<Integer> temp=new ArrayList<Integer>();
			ArrayList<Integer> old= (ArrayList<Integer>)t.clone();
			for(int i =0;i<iter;++i)
			{
				//the initial t is taken as oldsolution, and is copied to temp
				//inorder to reverse change if newfitness is worse
				double oldfitness = TSPSolution.TSPFitness(N, old, D);
				//System.out.println("old fitness:"+oldfitness);
				temp.clear();
				temp=TSPSolution.Clone(old);
				
				
				
				//small change applied to t and this fitness is taken as newfitness
				Swap(old);
				/*
				for(int j=0;j<old.size();++j){
					System.out.print(old.get(j)+" ");
				}
				System.out.println();
				*/
				double newfitness = TSPSolution.TSPFitness(N, old, D);
				//System.out.println("new fitness:"+newfitness);
				
				//compares the two fitness and if the old fitness is better,
				//copies the temp file back to t;
				if(newfitness>oldfitness)
					{			
						old.clear();
						old=TSPSolution.Clone(temp);
					
					}
				
			
			}	
			return old;	
		}
		
	public static  void Swap(ArrayList<Integer> t){
			int i=0,j=0;
			while (i==j){
				i = CS2004.UI(0,(t.size()-1));
				j = CS2004.UI(0,(t.size()-1));
			}
			int temp = t.get(i);
			t.set(i, t.get(j));
			t.set(j, temp);
		}
	public static ArrayList<Integer> RRHC(int N,double[][] D,int iter1, int iter2)
	{
		ArrayList<Integer> temp1=new ArrayList<Integer>();
		ArrayList<Integer> sol1= new ArrayList<Integer>();
		ArrayList<Integer> perm=new ArrayList<Integer>();
		for(int i=0;i<iter2;++i){
			
			perm = TSPSolution.RandPerm1(D);
			
			if(i==0){
				sol1 = RMHC(N,perm,D,iter1);
			}
			double oldfitness = TSPSolution.TSPFitness(N, sol1, D);
			//System.out.println("o:"+oldfitness);
			temp1.clear();
			temp1=TSPSolution.Clone(sol1);
			sol1 = RMHC(N,perm,D,iter1);
			double newfitness = TSPSolution.TSPFitness(N, sol1, D);
			if(newfitness>oldfitness)
			{			
				sol1.clear();
				sol1=TSPSolution.Clone(temp1);
			
			}
			
			perm.clear();
		}
		return sol1;	
	}

	public static ArrayList<Integer> SHC(int N, ArrayList<Integer> t, double[][] D,int iter)
	{
		
		ArrayList<Integer> temp=new ArrayList<Integer>();
		ArrayList<Integer> old= (ArrayList<Integer>)t.clone();
		for(int i =0;i<iter;++i)
		{
			//the initial t is taken as oldsolution, and is copied to temp
			//inorder to reverse change if newfitness is worse
			double oldfitness = TSPSolution.TSPFitness(N, old, D);
			//System.out.println("old fitness:"+oldfitness);
			temp.clear();
			temp=TSPSolution.Clone(old);
			
			//small change applied to t and this fitness is taken as newfitness
			Swap(old);
			double newfitness = TSPSolution.TSPFitness(N, old, D);
			//System.out.println("new fitness:"+newfitness);
			
			//compares the two fitness and if the old fitness is better,
			//copies the temp file back to t;
			double T=2.411111111;
	        double prAccept = 1/(1+Math.exp((newfitness-oldfitness)/T));
			if(newfitness>oldfitness){
				if (Math.random()<prAccept){			
					
				}
				else{
					old.clear();
					old=TSPSolution.Clone(temp);
				}
			}
			
		
		}	
		return old;	
	}
	public static ArrayList<Integer> SA(int N, ArrayList<Integer> t, double[][] D,int iter, double temperature, double lamda)
	{
		
		ArrayList<Integer> temp=new ArrayList<Integer>();
		ArrayList<Integer> old= (ArrayList<Integer>)t.clone();
		for(int i =0;i<iter;++i)
		{
			//the initial t is taken as oldsolution, and is copied to temp
			//inorder to reverse change if newfitness is worse
			double oldfitness = TSPSolution.TSPFitness(N, old, D);
			System.out.println("old fitness:"+oldfitness);
			temp.clear();
			temp=TSPSolution.Clone(old);
			
			
			
			//small change applied to t and this fitness is taken as newfitness
			Swap(old);
			/*
			for(int j=0;j<old.size();++j){
				System.out.print(old.get(j)+" ");
			}
			System.out.println();
			*/
			double newfitness = TSPSolution.TSPFitness(N, old, D);
			//System.out.println("new fitness:"+newfitness);
			
			//compares the two fitness and if the old fitness is better,
			//copies the temp file back to t;
			if(newfitness>oldfitness)
				{			
				double p=Pr(oldfitness,newfitness,temperature);	
				if(p<CS2004.UR(0, 1)){
					old.clear();
					old=TSPSolution.Clone(temp);
				}
			}
			temperature = temperature*lamda;
			
		
		}	
		return old;	
	}

	public static double Pr(double f, double f1, double T){
		
		double pr = Math.exp(-(Math.abs(f-f1))/T);
		return pr;
	}
	public static void report(int iter, double fit, double quality){
		
	}
	public static void WritingData(String File, int iter,ArrayList<Integer> perm ) throws IOException
	{
		int[] batch ={100} ;
		int len = 1;
		for(int i=0;i<len;++i){
			
			int file2 = batch[i];
			System.out.println(file2);
			ArrayList<Integer> solution = new ArrayList<Integer>();
			String filename = "C:\\TSP\\TSP_"+file2+".txt";
		
			double[][] x = null;
			//x = null;
			x = TSP.ReadArrayFile(filename, " ");
			int N =  x.length-1;
			System.out.println(N);
			double [][] mst =null;
			
			mst = MST.PrimsMST(x);
			
			
			
			
			
			double total=0;
			double avg=0;
			FileWriter writehandle = new FileWriter(File);
			BufferedWriter bw = new BufferedWriter(writehandle);
			
			//write RMHC to file
			double start = System.nanoTime();
			for(int j=0;j<10;++j){
				solution = RMHC(N, perm,x, iter);
				double fit = TSPSolution.TSPFitness(N, solution, x);
				total = total+fit; 
			}
			double mstL = TSPSolution.TSPFitness(N, solution, mst);
			double solF = TSPSolution.TSPFitness(solution.size(), solution, x);
			
			double eff = (mstL/solF)*100;
			double avgRun = (System.nanoTime() - start)/10;
			avg=total/10;
			String l = "Type "+"file " +"Average Fitness "+"MST Cost " + "Average Runtime " +"Efficency"; 
			String l2 = "RMHC "+filename+" "+avg+" "+mstL+" " +avgRun+" "+ eff;
			bw.write(l);
			bw.newLine();
			bw.write(l2);
			bw.newLine();
			System.out.println("RMHC Complete");
			//SHC to file
			solution.clear();
			start = System.nanoTime();
			for(int j=0;j<10;++j){
				solution = SHC(N,perm, x,iter);
				double fit = TSPSolution.TSPFitness(N, solution, x);
				total = total+fit; 
			}
			solF = TSPSolution.TSPFitness(solution.size(), solution, x);
			
			eff = (mstL/solF)*100;
			avgRun = (System.nanoTime() - start)/10;
			avg=total/10;
			l2 = "RRHC "+filename+" "+avg+" "+mstL+" " +avgRun+" "+ eff;
			bw.write(l2);
			bw.newLine();
			
			System.out.println("SHC Complete");
			//RRHC to file
			solution.clear();
			start = System.nanoTime();
			for(int j=0;j<10;++j){
				solution = RRHC(N, x,10000, 10);
				double fit = TSPSolution.TSPFitness(N, solution, x);
				total = total+fit; 
			}
			solF = TSPSolution.TSPFitness(solution.size(), solution, x);
			
			eff = (mstL/solF)*100;
			avgRun = (System.nanoTime() - start)/10;
			avg=total/10;
			l2 = "RRHC "+filename+" "+avg+" "+mstL+" " +avgRun+" "+ eff;
			bw.write(l2);
			bw.newLine();
			System.out.println("RRHC Complete");
			//SA to file
			double t0 = 150;
			double lamda = Math.pow((0.1/t0), 1/iter);
			solution.clear();
			start = System.nanoTime();
			for(int j=0;j<10;++j){
				solution = SA(N,perm, x,iter,t0,lamda);
				double fit = TSPSolution.TSPFitness(N, solution, x);
				total = total+fit; 
			}
			solF = TSPSolution.TSPFitness(solution.size(), solution, x);
			
			eff = (mstL/solF)*100;
			avgRun = (System.nanoTime() - start)/10;
			avg=total/10;
			l2 = "RRHC "+filename+" "+avg+" "+mstL+" " +avgRun+" "+ eff;
			bw.write(l2);
			bw.newLine();
			System.out.println("SA Complete");
			
			
			bw.close();
			writehandle.close();
		}
	}

}
