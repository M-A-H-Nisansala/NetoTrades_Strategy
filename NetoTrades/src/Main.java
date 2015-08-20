import java.util.ArrayList;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Calculator calculator = new Calculator();
		
		RegressionAnalyser regressionAnalyser = new RegressionAnalyser(calculator);	
		BayesianLearner bayesianLearner = new BayesianLearner(calculator);
		AdaptiveConcessionStrategy ConcessionStrategy = new AdaptiveConcessionStrategy(calculator);
		
		double itemPriceGuess = 1600.00;		
		int numberOfRounds = 15;
			
		Date currentTime= new Date();

		//Set the private information values for this agent	
		Agent agent1 = new Agent(1650.00, new Date(currentTime.getTime()+36000000));
		
		ArrayList<Offer> offerHistory = new ArrayList<Offer>();
		//Initialize detection region for other agent
		
		int numberOfRows = 10;
		int numberOfColumns = 10;
		
		DetectionRegion detReg = new DetectionRegion(itemPriceGuess/2, itemPriceGuess*2, currentTime, new Date(currentTime.getTime()+42000000));
		Cell[][] cells = new Cell[numberOfRows][numberOfColumns];
		Cell newCell;
		double cellLowerPrice;
		double cellUpperPrice;
		Date cellLowerDate;
		Date cellUpperDate;
		double cellReservePrice;
		Date cellDeadline;
		double cellProbability;
		
		for(int i = 0; i < numberOfRows; i++){
			for(int j = 0; j < numberOfColumns; j++){
				cellLowerPrice = detReg.getLowerReservePrice()+((detReg.getUpperReservePrice()-detReg.getLowerReservePrice())/numberOfRows)*j; 
				cellUpperPrice=detReg.getLowerReservePrice()+((detReg.getUpperReservePrice()-detReg.getLowerReservePrice())/numberOfRows)*(j+1); 
				cellLowerDate= new Date(detReg.getLowerDeadline().getTime()+ ((detReg.getUpperDeadline().getTime()-detReg.getLowerDeadline().getTime())/numberOfColumns)*i);
				cellUpperDate= new Date(detReg.getLowerDeadline().getTime()+ ((detReg.getUpperDeadline().getTime()-detReg.getLowerDeadline().getTime())/numberOfColumns)*(i+1));
				cellReservePrice=(cellUpperPrice + cellLowerPrice)/2;
				cellDeadline = new Date((cellUpperDate.getTime() + cellLowerDate.getTime())/2);
				cellProbability= 1/(numberOfRows*numberOfColumns);
				
				newCell = new Cell(cellLowerPrice, cellUpperPrice, cellLowerDate, cellUpperDate, cellReservePrice, cellDeadline, cellProbability);
				cells[i][j] = newCell;
			}
		}
		
		detReg.setCells(cells);
		detReg.setNumberOfCells(numberOfRows*numberOfColumns);
				
		double currentPriceOffer = 1500.00;
		int currentRound = 4;
		Offer newOffer = new Offer(currentPriceOffer, currentTime, currentRound);
		offerHistory.add(newOffer);
		
		int round = 0;
		long stepSize = (newOffer.getOfferTime().getTime() - offerHistory.get(0).getOfferTime().getTime()) / (newOffer.getRoundNumber() - 1);
		
		while(round < numberOfRounds){
			
			regressionAnalyser.Analyse(detReg, numberOfRows, numberOfColumns, offerHistory, numberOfRounds, newOffer);
			bayesianLearner.Learn(numberOfRows, numberOfColumns, detReg);
			ConcessionStrategy.FindConcessionPoint(numberOfRows, numberOfColumns, detReg, newOffer, agent1, stepSize, numberOfRounds, offerHistory);
			ConcessionStrategy.GenerateNextOffer(detReg, agent1, numberOfRows, numberOfColumns, newOffer, stepSize);
			
			round++;
		}
	}

}
